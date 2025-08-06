package net.cosmic.cdepth.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.cosmic.cdepth.component.ModDataComponentTypes;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class CrimsonChargeEnchantmentUserDamagedEffect implements EnchantmentEntityEffect {
    public static final MapCodec<CrimsonChargeEnchantmentUserDamagedEffect> CODEC = MapCodec.unit(CrimsonChargeEnchantmentUserDamagedEffect::new);
    @Override public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity living) {
            ItemStack stack = living.getMainHandStack();
            int currValue = stack.getOrDefault(ModDataComponentTypes.CRIMSON_COMBO,0);
            if (currValue > 0) {user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(),
                    SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.PLAYERS, 0.5F,1.25F);
                user.getWorld().playSound(null, user.getX(),user.getY(),user.getZ(),
                        SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, SoundCategory.PLAYERS, 1.0F,1.25F);
                user.getWorld().addParticle(ParticleTypes.EXPLOSION, user.getX(), user.getY() + 1.0, user.getZ(), 1.0, 0.0, 0.0);
            } stack.set(ModDataComponentTypes.CRIMSON_COMBO,0);
        }
    }
    @Override public MapCodec<? extends EnchantmentEntityEffect> getCodec() {return CODEC;}
}
