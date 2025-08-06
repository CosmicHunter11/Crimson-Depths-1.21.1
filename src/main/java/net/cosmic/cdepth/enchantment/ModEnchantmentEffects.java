package net.cosmic.cdepth.enchantment;

import com.mojang.serialization.MapCodec;
import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.enchantment.custom.BurstEnchantmentEffect;
import net.cosmic.cdepth.enchantment.custom.CrimsonChargeEnchantmentEffect;
import net.cosmic.cdepth.enchantment.custom.CrimsonChargeEnchantmentUserDamagedEffect;
import net.cosmic.cdepth.enchantment.custom.EternalFlameEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> BURST = registerEntityEffect(
            "burst", BurstEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> ETERNAL_FLAME = registerEntityEffect(
            "eternal_flame", EternalFlameEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CRIMSON_CHARGE = registerEntityEffect(
            "crimson_charge", CrimsonChargeEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> CRIMSON_CHARGE_DAMAGED = registerEntityEffect(
            "crimson_charge_damaged", CrimsonChargeEnchantmentUserDamagedEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(CDepth.MOD_ID,name), codec);}
    public static void registerModEnchantmentEffects() {CDepth.LOGGER.info("Registering Enchantment Effects for " + CDepth.MOD_ID);}
}
