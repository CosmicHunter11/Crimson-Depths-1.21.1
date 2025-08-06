package net.cosmic.cdepth.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class EternalFlameEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<EternalFlameEnchantmentEffect> CODEC = MapCodec.unit(EternalFlameEnchantmentEffect::new);
    @Override public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (!user.getWorld().isClient) {
            if (!user.isOnFire()) {user.setOnFireFor(15 * level);}else if(user instanceof LivingEntity livingEntity) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 40,0,false,true));
            }}
    }
    @Override public MapCodec<? extends EnchantmentEntityEffect> getCodec() {return CODEC;}
}
