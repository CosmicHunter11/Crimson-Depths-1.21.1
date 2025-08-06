package net.cosmic.cdepth;

import net.cosmic.cdepth.block.ModBlocks;
import net.cosmic.cdepth.entity.ModEntities;
import net.cosmic.cdepth.entity.client.CrimslinRenderer;
import net.cosmic.cdepth.particle.ModParticles;
import net.cosmic.cdepth.particle.custom.CrimsonExplosionParticle;
import net.cosmic.cdepth.particle.custom.CrimsoniteLeavesParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.SweepAttackParticle;
import net.minecraft.client.render.RenderLayer;

public class CDepthClient implements ClientModInitializer {
    @Override public void onInitializeClient() {
        // BLOCKS //
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FRAUD_PLUSHIE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIMSONITE_ROOTS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIMSONITE_LEAVES, RenderLayer.getCutout());
        // PARTICLES //
        ParticleFactoryRegistry.getInstance().register(ModParticles.CRIMSONITE_LEAVES, CrimsoniteLeavesParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CRIMSON_EXPLOSION, CrimsonExplosionParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.CRIMSON_SWEEP, SweepAttackParticle.Factory::new);
        // GECKOLIB //
        EntityRendererRegistry.register(ModEntities.CRIMSLIN, CrimslinRenderer::new);
    }
}
