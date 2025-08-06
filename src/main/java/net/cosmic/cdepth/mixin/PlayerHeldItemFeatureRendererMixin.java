package net.cosmic.cdepth.mixin;

import net.cosmic.cdepth.item.custom.CrimsoniteLauncherItem;
import net.cosmic.cdepth.item.custom.CrimsoniteLongswordItem;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.render.entity.feature.PlayerHeldItemFeatureRenderer.class)
public class PlayerHeldItemFeatureRendererMixin {
    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    protected void renderItem(LivingEntity entity, ItemStack stack, ModelTransformationMode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!entity.isUsingItem()) {
            if ((entity.getMainArm() == Arm.RIGHT && arm == Arm.LEFT) && entity.getMainHandStack().getItem() instanceof CrimsoniteLongswordItem && !(entity.getOffHandStack().getItem() instanceof CrimsoniteLauncherItem)) {ci.cancel();return;}
            if ((entity.getMainArm() == Arm.LEFT && arm == Arm.RIGHT) && entity.getMainHandStack().getItem() instanceof CrimsoniteLongswordItem && !(entity.getOffHandStack().getItem() instanceof CrimsoniteLauncherItem)) {ci.cancel();return;}}
    }
}