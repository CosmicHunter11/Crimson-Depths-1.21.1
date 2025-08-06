package net.cosmic.cdepth.datagen;

import net.cosmic.cdepth.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {super(output, registriesFuture);}
    @Override protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.CRIMSONITE_BLOCK)
                .add(ModBlocks.CHISELED_CRIMSONITE)
                .add(ModBlocks.COBBLED_CRIMSONITE)
                .add(ModBlocks.COBBLED_CRIMSONITE_SLAB)
                .add(ModBlocks.COBBLED_CRIMSONITE_STAIRS)
                .add(ModBlocks.CRIMSONITE_BRICKS)
                .add(ModBlocks.CRIMSONITE_BRICKS_SLAB)
                .add(ModBlocks.CRIMSONITE_BRICKS_STAIRS)
                .add(ModBlocks.CRIMSONITE_BULB)
                .add(ModBlocks.POLISHED_CRIMSONITE)
                .add(ModBlocks.POLISHED_CRIMSONITE_SLAB)
                .add(ModBlocks.POLISHED_CRIMSONITE_STAIRS)
                .add(ModBlocks.CRIMSONITE_TILES)
                .add(ModBlocks.CRIMSONITE_TILES_SLAB)
                .add(ModBlocks.CRIMSONITE_TILES_STAIRS)
        ;
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CRIMSONITE_BULB)
        ;
    }
}
