package net.cosmic.cdepth.datagen;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.block.ModBlocks;
import net.cosmic.cdepth.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {super(output, registriesFuture);}
    @Override public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.CRIMSONITE_INGOT,
                RecipeCategory.MISC, ModBlocks.CRIMSONITE_BLOCK);

        // COBBLED CRIMSONITE -> ITEM //
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS
                ,ModBlocks.COBBLED_CRIMSONITE_STAIRS, ModBlocks.COBBLED_CRIMSONITE);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COBBLED_CRIMSONITE_SLAB, ModBlocks.COBBLED_CRIMSONITE,2);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_CRIMSONITE, ModBlocks.COBBLED_CRIMSONITE);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_CRIMSONITE_STAIRS, ModBlocks.COBBLED_CRIMSONITE);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_CRIMSONITE_SLAB, ModBlocks.COBBLED_CRIMSONITE,2);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_BRICKS, ModBlocks.COBBLED_CRIMSONITE);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_BRICKS_STAIRS, ModBlocks.COBBLED_CRIMSONITE);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_BRICKS_SLAB, ModBlocks.COBBLED_CRIMSONITE,2);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_TILES, ModBlocks.COBBLED_CRIMSONITE);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_TILES_STAIRS, ModBlocks.COBBLED_CRIMSONITE);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_TILES_SLAB, ModBlocks.COBBLED_CRIMSONITE,2);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CHISELED_CRIMSONITE, ModBlocks.COBBLED_CRIMSONITE);
        // CRIMSONITE TILES -> ITEM //
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_TILES_STAIRS, ModBlocks.CRIMSONITE_TILES);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_TILES_SLAB, ModBlocks.CRIMSONITE_TILES);
        // CRIMSONITE BRICKS -> ITEM //
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_BRICKS_STAIRS, ModBlocks.CRIMSONITE_BRICKS);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.CRIMSONITE_BRICKS_SLAB, ModBlocks.CRIMSONITE_BRICKS);
        // POLISHED CRIMSONITE -> ITEM //
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_CRIMSONITE_STAIRS, ModBlocks.POLISHED_CRIMSONITE);
        offerStonecuttingRecipe(exporter,RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.POLISHED_CRIMSONITE_SLAB, ModBlocks.POLISHED_CRIMSONITE);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CRIMSONITE_LONGSWORD)
                .pattern(" WI")
                .pattern("HCW")
                .pattern("DH ")
                .input('W', Items.NETHER_WART)
                .input('D', Items.DIAMOND_SWORD)
                .input('I', ModItems.CRIMSONITE_INGOT)
                .input('C', ModBlocks.COBBLED_CRIMSONITE)
                .input('H', ModItems.HEATED_NETHERITE_SCRAP)
                .criterion(hasItem(ModItems.CRIMSONITE_INGOT),conditionsFromItem(ModItems.CRIMSONITE_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CRIMSONITE_LAUNCHER)
                .pattern("WHW")
                .pattern("CBC")
                .pattern("CHC")
                .input('W', Items.NETHER_WART)
                .input('B', Items.NETHER_WART_BLOCK)
                .input('C', ModBlocks.COBBLED_CRIMSONITE)
                .input('H', ModItems.HEATED_NETHERITE_SCRAP)
                .criterion(hasItem(ModItems.HEATED_NETHERITE_SCRAP),conditionsFromItem(ModItems.HEATED_NETHERITE_SCRAP))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.CRIMSONITE_PARASOUL)
                .pattern("WIW")
                .pattern("HSH")
                .pattern(" S ")
                .input('W', Items.NETHER_WART)
                .input('S', Items.STICK)
                .input('I', ModItems.CRIMSONITE_INGOT)
                .input('H', ModItems.HEATED_NETHERITE_SCRAP)
                .criterion(hasItem(ModItems.CRIMSONITE_INGOT),conditionsFromItem(ModItems.CRIMSONITE_INGOT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COBBLED_CRIMSONITE,4)
                .pattern("WD")
                .pattern("DW")
                .input('W', Items.NETHER_WART)
                .input('D', Items.COBBLED_DEEPSLATE)
                .criterion(hasItem(Items.NETHER_WART),conditionsFromItem(Items.NETHER_WART))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CRIMSONITE_INGOT)
                .pattern("BWB")
                .pattern("WIW")
                .pattern("BWB")
                .input('W', Items.NETHER_WART)
                .input('I', ModItems.IMPURE_CRIMSONITE_INGOT)
                .input('B', Items.NETHER_WART_BLOCK)
                .criterion(hasItem(ModItems.IMPURE_CRIMSONITE_INGOT),conditionsFromItem(ModItems.IMPURE_CRIMSONITE_INGOT))
                .offerTo(exporter, Identifier.of(CDepth.MOD_ID,"crimsonite_ingot_from_crafting"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.IMPURE_CRIMSONITE_INGOT)
                .input(Items.MAGMA_BLOCK,4)
                .input(ModItems.HEATED_NETHERITE_SCRAP,4)
                .criterion(hasItem(ModItems.HEATED_NETHERITE_SCRAP),conditionsFromItem(ModItems.HEATED_NETHERITE_SCRAP))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FRAUD_PLUSHIE)
                .pattern(" B ")
                .pattern("RYR")
                .pattern(" B ")
                .input('B', Items.BLACK_WOOL)
                .input('R', Items.RED_WOOL)
                .input('Y', Items.YELLOW_WOOL)
                .criterion(hasItem(ModItems.CRIMSONITE_INGOT),conditionsFromItem(ModItems.CRIMSONITE_INGOT))
                .offerTo(exporter);
    }
}
