package net.cosmic.cdepth.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class CrimsoniteLeavesParticle extends SpriteBillboardParticle {
    private float field_43369;
    private final float field_43370;
    private final float field_43371;

    protected CrimsoniteLeavesParticle(ClientWorld clientWorld, double x, double y, double z,
                                       SpriteProvider spriteProvider, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);
        this.setSpriteForAge(spriteProvider);
        this.gravityStrength = 7.5E-4F;
        this.velocityMultiplier = 1F;
        this.velocityX *= 0;
        this.velocityY *= 0;
        this.velocityZ *= 0;
        this.scale *= this.random.nextFloat() * 0.75F + 0.2F;
        this.maxAge = 300;
        this.field_43369 = (float)Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
        this.field_43370 = this.random.nextFloat();
        this.field_43371 = (float)Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
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


    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.maxAge-- <= 0) {
            this.markDead();
        }

        if (!this.dead) {
            float f = (float)(300 - this.maxAge);
            float g = Math.min(f / 300.0F, 1.0F);
            double d = Math.cos(Math.toRadians((double)(this.field_43370 * 60.0F))) * 2.0 * Math.pow((double)g, 1.25);
            double e = Math.sin(Math.toRadians((double)(this.field_43370 * 60.0F))) * 2.0 * Math.pow((double)g, 1.25);
            this.velocityX += d * 0.0024999999441206455;
            this.velocityZ += e * 0.0024999999441206455;
            this.velocityY -= (double)this.gravityStrength;
            this.field_43369 += this.field_43371 / 20.0F;
            this.prevAngle = this.angle;
            this.angle += this.field_43369 / 20.0F;
            this.move(this.velocityX, this.velocityY, this.velocityZ);
            if (this.onGround || this.maxAge < 299 && (this.velocityX == 0.0 || this.velocityZ == 0.0)) {
                this.markDead();
            }

            if (!this.dead) {
                this.velocityX *= (double)this.velocityMultiplier;
                this.velocityY *= (double)this.velocityMultiplier;
                this.velocityZ *= (double)this.velocityMultiplier;
            }
        }
    }

    @Override public ParticleTextureSheet getType() {return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;}

    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;
        public Factory(SpriteProvider spriteProvider) {this.spriteProvider = spriteProvider;}
        @Nullable @Override public Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new CrimsoniteLeavesParticle(world,x,y,z,this.spriteProvider,velocityX,velocityY,velocityZ);
        }
    }
}
