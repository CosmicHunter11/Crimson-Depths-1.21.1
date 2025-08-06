package net.cosmic.cdepth.block;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.block.custom.CrimsoniteBulbBlock;
import net.cosmic.cdepth.block.custom.CrimsoniteLeavesBlock;
import net.cosmic.cdepth.block.custom.FraudPlushieBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
public class ModBlocks {
    // CRIMSONITE BLOCKS //
    public static final Block CRIMSONITE_BLOCK = registerBlock("crimsonite_block",new Block(AbstractBlock.Settings.create().strength(50.0F,1200.0F).sounds(BlockSoundGroup.NETHERITE).requiresTool()), new Item.Settings().fireproof());
    // COBBLED //
    public static final Block COBBLED_CRIMSONITE = registerBlock("cobbled_crimsonite",new Block(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    public static final Block COBBLED_CRIMSONITE_STAIRS = registerBlock("cobbled_crimsonite_stairs",new StairsBlock(COBBLED_CRIMSONITE.getDefaultState(),AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    public static final Block COBBLED_CRIMSONITE_SLAB = registerBlock("cobbled_crimsonite_slab",new SlabBlock(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    // POLISHED //
    public static final Block POLISHED_CRIMSONITE = registerBlock("polished_crimsonite",new Block(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    public static final Block POLISHED_CRIMSONITE_STAIRS = registerBlock("polished_crimsonite_stairs",new StairsBlock(COBBLED_CRIMSONITE.getDefaultState(),AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    public static final Block POLISHED_CRIMSONITE_SLAB = registerBlock("polished_crimsonite_slab",new SlabBlock(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    // BRICKS //
    public static final Block CRIMSONITE_BRICKS = registerBlock("crimsonite_bricks",new Block(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    public static final Block CRIMSONITE_BRICKS_STAIRS = registerBlock("crimsonite_bricks_stairs",new StairsBlock(COBBLED_CRIMSONITE.getDefaultState(),AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    public static final Block CRIMSONITE_BRICKS_SLAB = registerBlock("crimsonite_bricks_slab",new SlabBlock(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    // TILES //
    public static final Block CRIMSONITE_TILES = registerBlock("crimsonite_tiles",new Block(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    public static final Block CRIMSONITE_TILES_STAIRS = registerBlock("crimsonite_tiles_stairs",new StairsBlock(COBBLED_CRIMSONITE.getDefaultState(),AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    public static final Block CRIMSONITE_TILES_SLAB = registerBlock("crimsonite_tiles_slab",new SlabBlock(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    // VEGETATION //
    public static final Block CRIMSONITE_WOOD = registerBlock("crimsonite_wood",new Block(AbstractBlock.Settings.create().strength(2.0F).sounds(BlockSoundGroup.WOOD)), new Item.Settings());
    public static final Block CRIMSONITE_LOG = registerBlock("crimsonite_log",new PillarBlock(AbstractBlock.Settings.create().strength(2.0F).sounds(BlockSoundGroup.WOOD)), new Item.Settings());
    public static final Block CRIMSONITE_LEAVES = registerBlock("crimsonite_leaves",new CrimsoniteLeavesBlock(AbstractBlock.Settings.create().nonOpaque().strength(0.2F).sounds(BlockSoundGroup.GRASS)), new Item.Settings());
    public static final Block CRIMSONITE_ROOTS = registerBlock("crimsonite_roots",new HangingRootsBlock(AbstractBlock.Settings.create().replaceable().pistonBehavior(PistonBehavior.DESTROY).breakInstantly().noCollision().sounds(BlockSoundGroup.WART_BLOCK).offset(AbstractBlock.OffsetType.XZ)), new Item.Settings());
    // MISC //
    public static final Block CHISELED_CRIMSONITE = registerBlock("chiseled_crimsonite",new Block(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).requiresTool()), new Item.Settings());
    public static final Block CRIMSONITE_BULB = registerBlock("crimsonite_bulb",new CrimsoniteBulbBlock(AbstractBlock.Settings.create().strength(2.0F,6.0F).sounds(BlockSoundGroup.NETHER_BRICKS).luminance(state -> state.get(BulbBlock.LIT) ? 15 : 0).requiresTool()), new Item.Settings());
    public static final Block FRAUD_PLUSHIE = registerBlock("fraud_plushie",new FraudPlushieBlock(AbstractBlock.Settings.create().strength(0.8F).sounds(BlockSoundGroup.WOOL).nonOpaque()), new Item.Settings());

    private static Block registerBlock(String name, Block block, Item.Settings settings) {
        registerBlockItem(name,block, settings); return Registry.register(Registries.BLOCK, Identifier.of(CDepth.MOD_ID,name),block);}
    private static void registerBlockItem(String name, Block block, Item.Settings settings) {
        Registry.register(Registries.ITEM, Identifier.of(CDepth.MOD_ID,name),new BlockItem(block, settings));}
    public static void registerModBlocks() {CDepth.LOGGER.info("Registering Mod Blocks for mod: " + CDepth.MOD_ID);}
}
