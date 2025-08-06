package net.cosmic.cdepth.particle.custom;

import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

public class CrimsonExplosionParticle extends SonicBoomParticle {
    protected CrimsonExplosionParticle(ClientWorld clientWorld, double d, double e, double f, double g, SpriteProvider spriteProvider) {
        super(clientWorld, d, e, f, g, spriteProvider);
        this.maxAge = 16;
        this.scale = 2.75F;
        this.setSpriteForAge(spriteProvider);
    } public ParticleTextureSheet getType() {return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;}
}
