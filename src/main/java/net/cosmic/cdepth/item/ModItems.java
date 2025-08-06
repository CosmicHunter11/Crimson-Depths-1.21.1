package net.cosmic.cdepth.item;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.entity.ModEntities;
import net.cosmic.cdepth.item.custom.CrimsoniteArmorItem;
import net.cosmic.cdepth.item.custom.CrimsoniteLauncherItem;
import net.cosmic.cdepth.item.custom.CrimsoniteLongswordItem;
import net.cosmic.cdepth.item.custom.CrimsoniteParasoulItem;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
public class ModItems {
    public static final Item CRIMSONITE_INGOT = registerItem("crimsonite_ingot",new Item(new Item.Settings().fireproof()));
    public static final Item IMPURE_CRIMSONITE_INGOT = registerItem("impure_crimsonite_ingot",new Item(new Item.Settings()));
    public static final Item HEATED_NETHERITE_SCRAP = registerItem("heated_netherite_scrap",new Item(new Item.Settings()));

    public static final Item CRIMSLIN_SPAWN_EGG = registerItem("crimslin_spawn_egg",new SpawnEggItem(ModEntities.CRIMSLIN,-1,-1,new Item.Settings()));

    public static final Item CRIMSONITE_LONGSWORD = registerItem("crimsonite_longsword",new CrimsoniteLongswordItem(new Item.Settings().maxCount(1).fireproof()
            .attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.NETHERITE,5,-3.0F))));
    public static final Item CRIMSONITE_PARASOUL = registerItem("crimsonite_parasoul",new CrimsoniteParasoulItem(new Item.Settings().fireproof().maxCount(1).maxDamage(750)));
    public static final Item CRIMSONITE_LAUNCHER = registerItem("crimsonite_launcher",new CrimsoniteLauncherItem(ToolMaterials.NETHERITE, BlockTags.SHOVEL_MINEABLE, new Item.Settings().fireproof().maxCount(1).maxDamage(750)));

    public static final Item CRIMSONITE_HELMET = registerItem("crimsonite_helmet",new CrimsoniteArmorItem(ArmorItem.Type.HELMET, new Item.Settings().maxCount(1).fireproof()));
    public static final Item CRIMSONITE_CHESTPLATE = registerItem("crimsonite_chestplate",new CrimsoniteArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1).fireproof()));
    public static final Item CRIMSONITE_LEGGINGS = registerItem("crimsonite_leggings",new CrimsoniteArmorItem(ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1).fireproof()));
    public static final Item CRIMSONITE_BOOTS = registerItem("crimsonite_boots",new CrimsoniteArmorItem(ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1).fireproof()));

    private static Item registerItem(String name, Item item) {return Registry.register(Registries.ITEM, Identifier.of(CDepth.MOD_ID, name), item);}
    public static void registerModItems() {CDepth.LOGGER.info("Registering Mod Items for mod: " + CDepth.MOD_ID);}
}
