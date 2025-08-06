package net.cosmic.cdepth.item.custom;

import net.cosmic.cdepth.sound.ModSounds;
import net.cosmic.cdepth.util.ModTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CrimsoniteParasoulItem extends Item {
    public static final int MAX_USAGE_TIME = 72000;
    public CrimsoniteParasoulItem(Settings settings) {super(settings);}

    @Override public UseAction getUseAction(ItemStack stack) {return UseAction.SPEAR;}
    @Override public int getMaxUseTime(ItemStack stack, LivingEntity user) {return MAX_USAGE_TIME;}
    @Override public boolean isUsedOnRelease(ItemStack stack) {return stack.isOf(this);}
    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {user.setCurrentHand(hand); return TypedActionResult.consume(user.getStackInHand(hand));}
    @Override public int getEnchantability() {return 1;}

    @Override public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int charge = MAX_USAGE_TIME - remainingUseTicks;
        boolean hasEnchant = stack.hasEnchantments() && EnchantmentHelper.hasAnyEnchantmentsIn(stack, ModTags.Enchantments.CAUSES_PARASOUL_DASH);
        System.out.println(EnchantmentHelper.hasAnyEnchantmentsIn(stack, ModTags.Enchantments.CAUSES_PARASOUL_DASH));
        if (!world.isClient) {
            if (hasEnchant && charge > 10) {
                this.launch(stack,world,user,100,2.25);
            } else if (!hasEnchant && charge > 3) {
                this.launch(stack,world,user,50,1.5);
            }
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    protected void launch(ItemStack stack, World world, LivingEntity user, int cooldownTime, double velocityMultiplier) {
        if (user instanceof PlayerEntity plr) {
            plr.getItemCooldownManager().set(this, cooldownTime);
            plr.useRiptide(15,4,stack);
        }
        applyVelocity(user, velocityMultiplier);
        world.playSound(null,user.getBlockPos(), ModSounds.PARASOUL_DASH, SoundCategory.PLAYERS,
                1.15F,0.85F);
        stack.damage(1,user,LivingEntity.getSlotForHand(user.getActiveHand()));
        user.move(MovementType.SELF, new Vec3d(0.0, 1.1999999284744263, 0.0));
    }

    @Override public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!entity.getWorld().isClient && entity instanceof PlayerEntity user && user.fallDistance > 0) {if (user.getMainHandStack() == stack || user.getOffHandStack() == stack) {
            if (!(user.getMainHandStack().getItem() instanceof SwordItem) && !(user.getMainHandStack().getItem() instanceof AxeItem)) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2,0,true,false));
            }
        }}
    }

    private void applyVelocity(LivingEntity user, double multiplier) {
        Vec3d direction = user.getRotationVector().multiply(multiplier);
        user.setVelocity(direction.x, direction.y, direction.z);
        user.velocityModified = true;
        user.fallDistance = 0;
    }
}
