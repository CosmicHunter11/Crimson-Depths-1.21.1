package net.cosmic.cdepth.item;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup CRIMSONITE = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CDepth.MOD_ID,"crimsonite"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.CRIMSONITE_INGOT))
                    .displayName(Text.translatable("itemgroup.cdepth.crimsonite_items"))
                    .entries(((displayContext, entries) -> {
                        entries.add(ModItems.CRIMSONITE_INGOT);
                        entries.add(ModItems.IMPURE_CRIMSONITE_INGOT);
                        entries.add(ModItems.HEATED_NETHERITE_SCRAP);
                        entries.add(ModItems.CRIMSONITE_LONGSWORD);
                        entries.add(ModItems.CRIMSONITE_PARASOUL);
                        entries.add(ModItems.CRIMSONITE_LAUNCHER);

                        entries.add(ModItems.CRIMSLIN_SPAWN_EGG);

                        entries.add(ModItems.CRIMSONITE_HELMET);
                        entries.add(ModItems.CRIMSONITE_CHESTPLATE);
                        entries.add(ModItems.CRIMSONITE_LEGGINGS);
                        entries.add(ModItems.CRIMSONITE_BOOTS);

                        entries.add(ModBlocks.CRIMSONITE_BLOCK);
                        entries.add(ModBlocks.COBBLED_CRIMSONITE);
                        entries.add(ModBlocks.COBBLED_CRIMSONITE_SLAB);
                        entries.add(ModBlocks.COBBLED_CRIMSONITE_STAIRS);
                        entries.add(ModBlocks.CRIMSONITE_BRICKS);
                        entries.add(ModBlocks.CRIMSONITE_BRICKS_SLAB);
                        entries.add(ModBlocks.CRIMSONITE_BRICKS_STAIRS);
                        entries.add(ModBlocks.CRIMSONITE_TILES);
                        entries.add(ModBlocks.CRIMSONITE_TILES_SLAB);
                        entries.add(ModBlocks.CRIMSONITE_TILES_STAIRS);
                        entries.add(ModBlocks.POLISHED_CRIMSONITE);
                        entries.add(ModBlocks.POLISHED_CRIMSONITE_SLAB);
                        entries.add(ModBlocks.POLISHED_CRIMSONITE_STAIRS);
                        entries.add(ModBlocks.CHISELED_CRIMSONITE);
                        entries.add(ModBlocks.CRIMSONITE_ROOTS);
                        entries.add(ModBlocks.CRIMSONITE_LEAVES);
                        entries.add(ModBlocks.CRIMSONITE_BULB);
                        entries.add(ModBlocks.FRAUD_PLUSHIE);
                        entries.add(ModBlocks.CRIMSONITE_WOOD);
                        entries.add(ModBlocks.CRIMSONITE_LOG);

                    })).build());

    public static void registerModItemGroups() {
        CDepth.LOGGER.info("Registering Mod Item Groups for mod: " + CDepth.MOD_ID);
    }
}
