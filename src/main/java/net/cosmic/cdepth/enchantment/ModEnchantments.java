package net.cosmic.cdepth.enchantment;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.enchantment.custom.BurstEnchantmentEffect;
import net.cosmic.cdepth.enchantment.custom.CrimsonChargeEnchantmentEffect;
import net.cosmic.cdepth.enchantment.custom.CrimsonChargeEnchantmentUserDamagedEffect;
import net.cosmic.cdepth.enchantment.custom.EternalFlameEnchantmentEffect;
import net.cosmic.cdepth.util.ModTags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> BURST = RegistryKey.of(RegistryKeys.ENCHANTMENT,
            Identifier.of(CDepth.MOD_ID,"burst"));
    public static final RegistryKey<Enchantment> ETERNAL_FLAME = RegistryKey.of(RegistryKeys.ENCHANTMENT,
            Identifier.of(CDepth.MOD_ID,"eternal_flame"));
    public static final RegistryKey<Enchantment> CRIMSON_CHARGE = RegistryKey.of(RegistryKeys.ENCHANTMENT,
            Identifier.of(CDepth.MOD_ID,"crimson_charge"));
    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);
        register(registerable, BURST, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModTags.Items.PARASOUL_ENCHANTABLE),
                items.getOrThrow(ModTags.Items.PARASOUL_ENCHANTABLE),
                5, // WEIGHT //
                1, // MAX LEVEL //
                Enchantment.leveledCost(3,0), // MIN COST //
                Enchantment.leveledCost(50,0), // MAX COST //
                2, // ANVIL COST //
                AttributeModifierSlot.HAND))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER,EnchantmentEffectTarget.VICTIM,
                        new BurstEnchantmentEffect())
        );
        register(registerable, ETERNAL_FLAME, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModTags.Items.CRIMSONITE_LONGSWORD_ENCHANTABLE),
                items.getOrThrow(ModTags.Items.CRIMSONITE_LONGSWORD_ENCHANTABLE),
                5, // WEIGHT //
                1, // MAX LEVEL //
                Enchantment.leveledCost(3,0), // MIN COST //
                Enchantment.leveledCost(50,0), // MAX COST //
                2, // ANVIL COST //
                AttributeModifierSlot.HAND))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER,EnchantmentEffectTarget.VICTIM,
                        new EternalFlameEnchantmentEffect())
        );
        register(registerable, CRIMSON_CHARGE, Enchantment.builder(Enchantment.definition(
                items.getOrThrow(ModTags.Items.CRIMSONITE_LONGSWORD_ENCHANTABLE),
                items.getOrThrow(ModTags.Items.CRIMSONITE_LONGSWORD_ENCHANTABLE),
                5, // WEIGHT //
                1, // MAX LEVEL //
                Enchantment.leveledCost(3,0), // MIN COST //
                Enchantment.leveledCost(50,0), // MAX COST //
                2, // ANVIL COST //
                AttributeModifierSlot.MAINHAND))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER,EnchantmentEffectTarget.VICTIM,
                        new CrimsonChargeEnchantmentEffect())
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.VICTIM,EnchantmentEffectTarget.VICTIM,
                        new CrimsonChargeEnchantmentUserDamagedEffect())
        );

    }
    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key,builder.build(key.getValue()));}
    public static void registerModEnchantments() {CDepth.LOGGER.info("Registering Enchantments for " + CDepth.MOD_ID);}
}
