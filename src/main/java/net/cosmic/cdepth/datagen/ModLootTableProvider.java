package net.cosmic.cdepth.datagen;

import net.cosmic.cdepth.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {super(dataOutput, registryLookup);}

    @Override public void generate() {
        addDrop(ModBlocks.CRIMSONITE_BLOCK);
        addDrop(ModBlocks.CRIMSONITE_ROOTS);
        addDrop(ModBlocks.FRAUD_PLUSHIE);
        addDrop(ModBlocks.CHISELED_CRIMSONITE);
        addDrop(ModBlocks.COBBLED_CRIMSONITE);
        addDrop(ModBlocks.COBBLED_CRIMSONITE_SLAB, slabDrops(ModBlocks.COBBLED_CRIMSONITE_SLAB));
        addDrop(ModBlocks.COBBLED_CRIMSONITE_STAIRS);
        addDrop(ModBlocks.CRIMSONITE_BRICKS);
        addDrop(ModBlocks.CRIMSONITE_BRICKS_SLAB, slabDrops(ModBlocks.CRIMSONITE_BRICKS_SLAB));
        addDrop(ModBlocks.CRIMSONITE_BRICKS_STAIRS);
        addDrop(ModBlocks.CRIMSONITE_BULB);
        addDrop(ModBlocks.CRIMSONITE_LOG);
        addDrop(ModBlocks.CRIMSONITE_TILES);
        addDrop(ModBlocks.CRIMSONITE_TILES_SLAB, slabDrops(ModBlocks.CRIMSONITE_TILES_SLAB));
        addDrop(ModBlocks.CRIMSONITE_TILES_STAIRS);
        addDrop(ModBlocks.CRIMSONITE_WOOD);
        addDrop(ModBlocks.POLISHED_CRIMSONITE);
        addDrop(ModBlocks.POLISHED_CRIMSONITE_SLAB, slabDrops(ModBlocks.POLISHED_CRIMSONITE_SLAB));
        addDrop(ModBlocks.POLISHED_CRIMSONITE_STAIRS);
        addDrop(ModBlocks.CRIMSONITE_LEAVES,leavesDrops(ModBlocks.CRIMSONITE_LEAVES,ModBlocks.FRAUD_PLUSHIE,0));
    }
}
