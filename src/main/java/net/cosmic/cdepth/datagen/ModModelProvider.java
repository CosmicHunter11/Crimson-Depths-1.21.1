package net.cosmic.cdepth.datagen;

import net.cosmic.cdepth.block.ModBlocks;
import net.cosmic.cdepth.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {super(output);}
    @Override public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRIMSONITE_LEAVES);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRIMSONITE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHISELED_CRIMSONITE);

        BlockStateModelGenerator.BlockTexturePool polishedCrimsonitePool = blockStateModelGenerator
                .registerCubeAllModelTexturePool(ModBlocks.POLISHED_CRIMSONITE);
        BlockStateModelGenerator.BlockTexturePool crimsoniteTilesPool = blockStateModelGenerator
                .registerCubeAllModelTexturePool(ModBlocks.CRIMSONITE_TILES);
        BlockStateModelGenerator.BlockTexturePool crimsoniteBricksPool = blockStateModelGenerator
                .registerCubeAllModelTexturePool(ModBlocks.CRIMSONITE_BRICKS);
        BlockStateModelGenerator.BlockTexturePool cobbledCrimsonitePool = blockStateModelGenerator
                .registerCubeAllModelTexturePool(ModBlocks.COBBLED_CRIMSONITE);

        polishedCrimsonitePool.stairs(ModBlocks.POLISHED_CRIMSONITE_STAIRS);
        polishedCrimsonitePool.slab(ModBlocks.POLISHED_CRIMSONITE_SLAB);
        crimsoniteTilesPool.stairs(ModBlocks.CRIMSONITE_TILES_STAIRS);
        crimsoniteTilesPool.slab(ModBlocks.CRIMSONITE_TILES_SLAB);
        crimsoniteBricksPool.stairs(ModBlocks.CRIMSONITE_BRICKS_STAIRS);
        crimsoniteBricksPool.slab(ModBlocks.CRIMSONITE_BRICKS_SLAB);
        cobbledCrimsonitePool.stairs(ModBlocks.COBBLED_CRIMSONITE_STAIRS);
        cobbledCrimsonitePool.slab(ModBlocks.COBBLED_CRIMSONITE_SLAB);
    }

    @Override public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CRIMSONITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.IMPURE_CRIMSONITE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.HEATED_NETHERITE_SCRAP, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSLIN_SPAWN_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSONITE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSONITE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSONITE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSONITE_BOOTS, Models.GENERATED);
    }
}
