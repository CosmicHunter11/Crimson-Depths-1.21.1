package net.cosmic.cdepth.entity.client;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.entity.custom.CrimslinEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrimslinRenderer extends GeoEntityRenderer<CrimslinEntity> {
    public CrimslinRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CrimslinModel());
        this.shadowRadius = 0.5F;
    }

    @Override public void render(CrimslinEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        poseStack.scale(0.85F,0.85F,0.85F);
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
    @Override public Identifier getTexture(CrimslinEntity crimslin) {
        return Identifier.of(CDepth.MOD_ID, "textures/entity/crimslin.png");}
    @Override public @Nullable RenderLayer getRenderType(CrimslinEntity crimslin, Identifier texture, @Nullable VertexConsumerProvider bufferSource, float partialTick) {
        return super.getRenderType(crimslin, texture, bufferSource, partialTick);}
}
