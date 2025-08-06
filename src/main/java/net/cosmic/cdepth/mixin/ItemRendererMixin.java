package net.cosmic.cdepth.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.item.ModItems;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow @Final private ItemModels models;
    @Shadow public abstract ItemModels getModels();
    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "HEAD"), argsOnly = true
    )
    public BakedModel renderItem(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack, @Local(argsOnly = true) ModelTransformationMode renderMode) {
        if (stack.getItem() == ModItems.CRIMSONITE_PARASOUL && !stack.getName().getString().equalsIgnoreCase("umbreaker")) {
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(CDepth.MOD_ID, "crimsonite_parasoul")));
        }

        return bakedModel;
    }

    @ModifyVariable(method = "getModel", at = @At(value = "STORE"), ordinal = 1)
    public BakedModel getHeldItemModelMixin(BakedModel bakedModel, @Local(argsOnly = true) ItemStack stack) {
        if (stack.getItem() == ModItems.CRIMSONITE_PARASOUL && stack.getName().getString().equalsIgnoreCase("umbreaker")) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Identifier.of(CDepth.MOD_ID, "umbreaker")));
        }
        return bakedModel;
    }
}
