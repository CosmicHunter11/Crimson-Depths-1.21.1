package net.cosmic.cdepth.item.custom;

import net.cosmic.cdepth.component.ModDataComponentTypes;
import net.cosmic.cdepth.entity.ModEntities;
import net.cosmic.cdepth.entity.custom.CrimslinEntity;
import net.cosmic.cdepth.item.ModItems;
import net.cosmic.cdepth.sound.ModSounds;
import net.cosmic.cdepth.util.ModTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class CrimsoniteLongswordItem extends SwordItem {
    public CrimsoniteLongswordItem(Settings settings) {
        super(ToolMaterials.NETHERITE, settings);
    }

    @Override public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(!target.getWorld().isClient) {
           // HEAVY CRIT
            target.getWorld().playSoundFromEntity(null, target, ModSounds.CRIMSON_SLICE,SoundCategory.PLAYERS, 0.825F,0.85F);
            if (attacker.fallDistance > 2.75F) {
                if (attacker.fallDistance > 7.5F) {
                    target.getWorld().playSoundFromEntity(null, target, ModSounds.CRIMSON_CRIT_HEAVY,SoundCategory.PLAYERS, 0.825F,1.0F);
                } else {
                    target.getWorld().playSoundFromEntity(null, target, ModSounds.CRIMSON_CRIT,SoundCategory.PLAYERS, 0.825F,1.0F);
                }
                attacker.fallDistance = 0;
            }
        } return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (!(player.getWorld().isClient()) && clickType == ClickType.RIGHT) {
            player.getWorld().playSound(null, player.getX(),player.getY(),player.getZ(),
                    ModSounds.CRIMSONITE_CAST, SoundCategory.PLAYERS, 0.75F,1.25F);
            int currValue = stack.getOrDefault(ModDataComponentTypes.TOGGLE_CRIMSLIN,0);
            int newValue = (currValue == 1) ? 0 : 1;
            stack.set(ModDataComponentTypes.TOGGLE_CRIMSLIN,newValue);
            return true;
        } return false;
    }
    @Override public boolean hasGlint(ItemStack stack) {return false;}
    @Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Pierce their heart.").formatted(Formatting.RED));
        int currValue = stack.getOrDefault(ModDataComponentTypes.TOGGLE_CRIMSLIN, 0);
        if (currValue == 1) {
            tooltip.add(Text.literal("Active").formatted(Formatting.GOLD));
        } else {
            tooltip.add(Text.literal("Deactive").formatted(Formatting.GRAY));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient && hand == Hand.MAIN_HAND) {
            int currValue = stack.getOrDefault(ModDataComponentTypes.TOGGLE_CRIMSLIN, 0);
            if (currValue == 1) {
                if (user.isCreative()) user.getItemCooldownManager().set(this, 10);
                else {user.getItemCooldownManager().set(this,750 - (80 * getArmorValue(user)));}
                for (int i = 0; i < 10; ++i) {world.addParticle(ParticleTypes.SMOKE, user.getParticleX(0.05), user.getRandomBodyY(), user.getParticleZ(0.05), 0,0,0);}
                CrimslinEntity crimslinEntity = new CrimslinEntity(ModEntities.CRIMSLIN, world);
                crimslinEntity.setOwner(user);
                crimslinEntity.setPosition(user.getX(),user.getY(),user.getZ());
                ItemStack offHandStack = user.getOffHandStack();
                if (offHandStack.getItem() == ModItems.CRIMSONITE_PARASOUL && !(user.getItemCooldownManager().isCoolingDown(ModItems.CRIMSONITE_PARASOUL))) {
                    Vec3d dir = user.getRotationVector().multiply(2.15);
                    crimslinEntity.setVelocity(dir); crimslinEntity.velocityModified = true;
                } world.spawnEntity(crimslinEntity);
            }
        }
        return super.use(world, user, hand);
    }

    public int getArmorValue(PlayerEntity user) {
        int value = 1;
        if(user.getInventory().getArmorStack(0).getItem() instanceof CrimsoniteArmorItem){value++;}
        if(user.getInventory().getArmorStack(1).getItem() instanceof CrimsoniteArmorItem){value++;}
        if(user.getInventory().getArmorStack(2).getItem() instanceof CrimsoniteArmorItem){value++;}
        if(user.getInventory().getArmorStack(3).getItem() instanceof CrimsoniteArmorItem){value++;}
        return value; }

    @Override public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!entity.getWorld().isClient && entity instanceof PlayerEntity user && user.fallDistance > 2.75F) {
            if (user.fallDistance > 7.5) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2,1,true,false));
            }
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2,0,true,false));
        } super.inventoryTick(stack, world, entity, slot, selected);
    }
}
