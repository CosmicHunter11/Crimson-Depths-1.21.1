package net.cosmic.cdepth.entity.custom;

import net.cosmic.cdepth.sound.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class CrimslinEntity extends TameableEntity implements GeoEntity {
    protected static final RawAnimation RUN_ANIM = RawAnimation.begin().thenLoop("animation.crimslin.walk");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.crimslin.idle");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    private static final int DESPAWN_TIME = 1420;
    private int lifeTime;

    // BASE ENTITY //
    public CrimslinEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER,-1.0F);
        this.setPathfindingPenalty(PathNodeType.LAVA, 0.0F);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 0.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 0.0F);
    }
    public static DefaultAttributeContainer.Builder setAttributes() {
        return TameableEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.5F)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1.5F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F);
    }

    // GOALS //
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this,1.20,false));
        this.goalSelector.add(3, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.add(4, new WanderAroundFarGoal(this,0.75,1));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.targetSelector.add(2, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));
        this.targetSelector.add(3, new AttackWithOwnerGoal(this));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, HostileEntity.class, true));
    }
    @Override public boolean canTarget(LivingEntity target) {
        if (target == null || target instanceof ArmorStandEntity || target instanceof CreeperEntity) {return false;}
        if (target instanceof TameableEntity entity) {if (entity.isTamed() && entity.getOwner() == this.getOwner()) {return false;}} return super.canTarget(target);
    }

    // MISC //
    @Override public boolean isBreedingItem(ItemStack stack) {return false;}
    @Override public boolean canWalkOnFluid(FluidState state) {return state.isIn(FluidTags.LAVA);}
    @Override public boolean hurtByWater() {return true;}
    @Override public boolean isOnFire() {return false;}
    @Override public boolean isFireImmune() {return true;}
    @Nullable @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {return null;}

    // NBT //
    public void readCustomDataFromNbt(NbtCompound nbt) {super.readCustomDataFromNbt(nbt); this.lifeTime = nbt.getInt("Lifetime");}
    public void writeCustomDataToNbt(NbtCompound nbt) {super.writeCustomDataToNbt(nbt); nbt.putInt("Lifetime", this.lifeTime);}

    // SOUNDS //
    @Override public void playAmbientSound() {this.playSound(ModSounds.CRIMSLIN_IDLE, 0.6F, 1.25F);}
    @Override protected void playStepSound(BlockPos pos, BlockState state) {this.playSound(SoundEvents.ENTITY_ZOMBIE_STEP, 0.45F, 2.0F);}
    @Nullable @Override protected SoundEvent getDeathSound() {return ModSounds.CRIMSLIN_DIED;}
    @Override protected void playHurtSound(DamageSource source) {this.playSound(ModSounds.CRIMSLIN_HURT, 0.6F, 1.25F);}

    // TICK //
    @Override public void tickMovement() {
        if (this.getWorld().isClient) {for (int i = 0; i < 2; ++i) {this.getWorld().addParticle(ParticleTypes.SMOKE, this.getParticleX(0.05), this.getRandomBodyY(), this.getParticleZ(0.05), 0,0,0);}}
        else {if (!this.isPersistent()) {++this.lifeTime;}
            if (this.lifeTime >= DESPAWN_TIME) {
                this.playSound(ModSounds.CRIMSLIN_DIED, 0.6F, 1.25F);
                this.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 0.6F, 1.25F);
                this.discard();
            }
        }
        super.tickMovement();
    }

    // ANIMATIONS //
    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(RUN_ANIM);
            return PlayState.CONTINUE;}
        event.getController().setAnimation(IDLE_ANIM);
        return PlayState.CONTINUE;
    }
    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this,"controller",5, this::predicate));
    }
    @Override public AnimatableInstanceCache getAnimatableInstanceCache() {return this.geoCache;}
}
