package net.cosmic.cdepth.damage_types;

import net.cosmic.cdepth.CDepth;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> CRIMSON_SOUL_CHARGE = registerDamageType("crimson_soul_charge");

    public static DamageSource ofWorld(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));}

    private static RegistryKey<DamageType> registerDamageType(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE,Identifier.of(CDepth.MOD_ID,name));}
    public static void registerModDamageTypes() {
        CDepth.LOGGER.info("Registering Mod Damage Types for " + CDepth.MOD_ID);
    }
}
