package net.cosmic.cdepth.mixin;

import net.cosmic.cdepth.item.custom.CrimsoniteLongswordItem;
import net.cosmic.cdepth.particle.ModParticles;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "spawnSweepAttackParticles", at = @At("HEAD"), cancellable = true)
    private void onSpawnSweepAttackParticles(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player.getMainHandStack().getItem() instanceof CrimsoniteLongswordItem) {
            ci.cancel();
            double d = -MathHelper.sin(player.getYaw() * 0.017453292F);
            double e = MathHelper.cos(player.getYaw() * 0.017453292F);
            if (player.getWorld() instanceof ServerWorld) {
                ((ServerWorld)player.getWorld()).spawnParticles(ModParticles.CRIMSON_SWEEP, player.getX() + d, player.getBodyY(0.5), player.getZ() + e, 0, d, 0.0, e, 0.0);
            }
        }
    }
}