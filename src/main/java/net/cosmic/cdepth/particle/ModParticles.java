package net.cosmic.cdepth.particle;

import net.cosmic.cdepth.CDepth;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModParticles {
    public static final SimpleParticleType CRIMSONITE_LEAVES = registerParticle("crimsonite_leaves", FabricParticleTypes.simple(false));
    public static final SimpleParticleType CRIMSON_SWEEP = registerParticle("crimson_sweep", FabricParticleTypes.simple(true));
    public static final SimpleParticleType CRIMSON_EXPLOSION = registerParticle("crimson_explosion", FabricParticleTypes.simple(true));

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(CDepth.MOD_ID,name),particleType);}
    public static void registerModParticles() {CDepth.LOGGER.info("Registering Mod Particles for mod: " + CDepth.MOD_ID);}
}
