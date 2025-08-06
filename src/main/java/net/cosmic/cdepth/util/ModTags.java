package net.cosmic.cdepth.util;

import net.cosmic.cdepth.CDepth;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(CDepth.MOD_ID,name));
        }
    }
    public static class Items {
        public static final TagKey<Item> PARASOUL_ENCHANTABLE = createTag("enchantable/parasoul");
        public static final TagKey<Item> CRIMSONITE_LONGSWORD_ENCHANTABLE = createTag("enchantable/crimsonite_longsword");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(CDepth.MOD_ID,name));
        }
    }
    public static class Enchantments {
        public static final TagKey<Enchantment> CAUSES_PARASOUL_DASH = createTag("causes_parasoul_dash");
        public static final TagKey<Enchantment> HAS_CRIMSON_CHARGE = createTag("has_crimson_charge");

        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(CDepth.MOD_ID,name));
        }
    }
}