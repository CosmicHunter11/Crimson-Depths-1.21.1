package net.cosmic.cdepth.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.cosmic.cdepth.component.ModDataComponentTypes;
import net.cosmic.cdepth.sound.ModSounds;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class CrimsonChargeEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<CrimsonChargeEnchantmentEffect> CODEC = MapCodec.unit(CrimsonChargeEnchantmentEffect::new);
    @Override public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity target) {
            LivingEntity attacker = target.getAttacker();
            ItemStack stack = attacker.getMainHandStack();

            int currValue = stack.getOrDefault(ModDataComponentTypes.CRIMSON_COMBO,0);
            if (currValue > 1) {
                target.setOnFireFor(8);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 50,0,false,true));
                target.getWorld().playSoundFromEntity(null, target, ModSounds.CRIMSONITE_CAST, SoundCategory.PLAYERS, 1.0F,1.35F);
                attacker.getWorld().playSoundFromEntity(null, attacker, SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 1.0F,1.5F);
            } else if (currValue > 0) {
                target.setOnFireFor(5);
                target.getWorld().playSoundFromEntity(null, target, ModSounds.CRIMSONITE_CAST, SoundCategory.PLAYERS, 0.9F, 1.0F);
                attacker.getWorld().playSoundFromEntity(null, attacker, SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 0.9F, 1.25F);
            }
            if (currValue > 2) {
                target.setOnFireFor(12);
                target.getWorld().playSoundFromEntity(null, target, ModSounds.CRIMSONITE_CAST, SoundCategory.PLAYERS, 1.1F,1.55F);
                attacker.getWorld().playSoundFromEntity(null, attacker, SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 1.1F,1.75F);
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 50,0,false,true));
            } else {
                stack.set(ModDataComponentTypes.CRIMSON_COMBO,currValue+1);
            }
        }
    }
    @Override public MapCodec<? extends EnchantmentEntityEffect> getCodec() {return CODEC;}
}
