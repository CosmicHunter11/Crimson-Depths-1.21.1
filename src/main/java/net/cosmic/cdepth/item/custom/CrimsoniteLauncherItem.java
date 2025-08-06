package net.cosmic.cdepth.item.custom;

import net.cosmic.cdepth.damage_types.ModDamageTypes;
import net.cosmic.cdepth.sound.ModSounds;
import net.cosmic.cdepth.particle.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class CrimsoniteLauncherItem extends MiningToolItem {
    public static final int MAX_USE_TIME = 72000;
    public CrimsoniteLauncherItem(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {super(material, effectiveBlocks, settings);}

    @Override public UseAction getUseAction(ItemStack stack) {return UseAction.BOW;}
    @Override public int getMaxUseTime(ItemStack stack, LivingEntity user) {return MAX_USE_TIME;}
    @Override public boolean isUsedOnRelease(ItemStack stack) {return stack.isOf(this);}
    @Override public boolean hasGlint(ItemStack stack) {return false;}
    @Override public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {return 1.25F;}

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand); return TypedActionResult.consume(user.getStackInHand(hand));}
    public boolean isExplodableEntity(Entity entity) {return entity instanceof LivingEntity || entity instanceof ProjectileEntity;}

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        // DEFAULT VALUES //
        double EXPLOSION_KB_MULTIPLIER = 1.25;
        double EXPLOSION_RADIUS = 3.0;
        double DAMAGE_COST = 1.0;

        int charge = MAX_USE_TIME - remainingUseTicks;

        if (charge > 10) {
            world.addParticle(ModParticles.CRIMSON_EXPLOSION, user.getX(), user.getY() + 1.0, user.getZ(), 1.0, 0.0, 0.0);

            if (charge > 20) {EXPLOSION_KB_MULTIPLIER=2.50;EXPLOSION_RADIUS=4.0;DAMAGE_COST=2;}
            if (charge > 40) {EXPLOSION_KB_MULTIPLIER=3.50;EXPLOSION_RADIUS=5.0;DAMAGE_COST=4;}

            if (!world.isClient && user instanceof PlayerEntity plr) {

                for (Entity entity : world.getOtherEntities(user,new Box(user.getX()-EXPLOSION_RADIUS,user.getY()-EXPLOSION_RADIUS,user.getZ()-EXPLOSION_RADIUS,user.getX()+EXPLOSION_RADIUS,user.getY()+EXPLOSION_RADIUS,user.getZ()+EXPLOSION_RADIUS))) {
                    if (isExplodableEntity(entity)) {
                        entity.damage(ModDamageTypes.ofWorld(world,DamageTypes.EXPLOSION),(float) DAMAGE_COST * 4.25F);

                        double dx = entity.getX() - user.getX();double dy = entity.getY() - user.getY();double dz = entity.getZ() - user.getZ();
                        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
                        if (distance > 0) {dx /= distance;dy /= distance;dz /= distance;}
                        entity.setVelocity(entity.getVelocity().add(dx * EXPLOSION_KB_MULTIPLIER, dy * EXPLOSION_KB_MULTIPLIER + 0.51, dz * EXPLOSION_KB_MULTIPLIER));
                        entity.velocityModified = true;
                    }
                }
                if (DAMAGE_COST == 1) {world.playSoundFromEntity(null,user, ModSounds.CRIMSON_EXPLOSION, SoundCategory.PLAYERS, 1.25F,1.25F);}
                if (DAMAGE_COST == 2) {world.playSoundFromEntity(null,user, ModSounds.CRIMSON_EXPLOSION, SoundCategory.PLAYERS, 1.25F,0.95F);}
                if (DAMAGE_COST == 4) {
                    world.playSoundFromEntity(null,user, ModSounds.CRIMSON_EXPLOSION, SoundCategory.PLAYERS, 1.25F,0.95F);
                    world.playSoundFromEntity(null,user, ModSounds.CRIMSON_EXPLOSION, SoundCategory.PLAYERS, 1.25F,0.85F);
                }

                world.playSoundFromEntity(null,user, SoundEvents.ENTITY_GENERIC_EXPLODE.value(), SoundCategory.PLAYERS, 1.25F,0.85F);
                plr.getItemCooldownManager().set(this,300);
                plr.damage(ModDamageTypes.ofWorld(world,ModDamageTypes.CRIMSON_SOUL_CHARGE), (float) DAMAGE_COST);

            }
        } super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int charge = MAX_USE_TIME - remainingUseTicks;
        if (charge == 11) {world.playSoundFromEntity(null,user, ModSounds.CRIMSONITE_LAUNCHER_CHARGE, SoundCategory.PLAYERS, 0.5F,0.85F);}
        if (charge == 21) {world.playSoundFromEntity(null,user, ModSounds.CRIMSONITE_LAUNCHER_CHARGE, SoundCategory.PLAYERS, 0.75F,1.0F);}
        if (charge == 41) {world.playSoundFromEntity(null,user, ModSounds.CRIMSONITE_LAUNCHER_CHARGE, SoundCategory.PLAYERS, 1.75F,1.175F);}
    }

    @Override public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity plr && plr.getItemCooldownManager().isCoolingDown(this)) {plr.getItemCooldownManager().set(this, 10);}
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}
