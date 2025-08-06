package net.cosmic.cdepth.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class CrimsoniteLeavesParticleBACKUP extends SpriteBillboardParticle {

    protected CrimsoniteLeavesParticleBACKUP(ClientWorld clientWorld, double x, double y, double z,
                                             SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);
        this.setSpriteForAge(spriteProvider);
        this.gravityStrength = 0.25F;
        this.velocityMultiplier = 1F;
        this.velocityX *= 0;
        this.velocityY *= 0;
        this.velocityZ *= 0;
        this.scale *= this.random.nextFloat() * 0.75F + 0.2F;
        this.maxAge = (int)(30.0 / (Math.random() * 0.8 + 0.2));
    }

    @Override protected int getBrightness(float tint) {
        int i = super.getBrightness(tint);
        int k = i >> 16 & 255;
        return 240 | k << 16;
    }

    @Override
    public float getSize(float tickDelta) {
        float f = ((float)this.age + tickDelta) / (float)this.maxAge;
        return this.scale * (1.0F - f * f);
    }

    @Override public ParticleTextureSheet getType() {return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;}

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;
        public Factory(SpriteProvider spriteProvider) {this.spriteProvider = spriteProvider;}
        @Nullable @Override public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new CrimsoniteLeavesParticleBACKUP(world,x,y,z,this.spriteProvider,velocityX,velocityY,velocityZ);
        }
    }
}
