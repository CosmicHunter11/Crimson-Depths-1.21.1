package net.cosmic.cdepth.datagen;

import net.cosmic.cdepth.item.ModItems;
import net.cosmic.cdepth.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {super(output, completableFuture);}
    @Override protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.PARASOUL_ENCHANTABLE).add(ModItems.CRIMSONITE_PARASOUL);
        getOrCreateTagBuilder(ModTags.Items.CRIMSONITE_LONGSWORD_ENCHANTABLE).add(ModItems.CRIMSONITE_LONGSWORD);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.CRIMSONITE_LONGSWORD)
                .add(ModItems.CRIMSONITE_PARASOUL)
                .add(ModItems.CRIMSONITE_LAUNCHER)
                .add(ModItems.CRIMSONITE_HELMET)
                .add(ModItems.CRIMSONITE_CHESTPLATE)
                .add(ModItems.CRIMSONITE_LEGGINGS)
                .add(ModItems.CRIMSONITE_BOOTS)
        ;
        getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE).add(ModItems.CRIMSONITE_LAUNCHER);
        getOrCreateTagBuilder(ItemTags.SWORDS).add(ModItems.CRIMSONITE_LONGSWORD);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR).add(ModItems.CRIMSONITE_CHESTPLATE);
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR).add(ModItems.CRIMSONITE_BOOTS);
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR).add(ModItems.CRIMSONITE_HELMET);
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR).add(ModItems.CRIMSONITE_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR_ENCHANTABLE).add(ModItems.CRIMSONITE_CHESTPLATE);
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR_ENCHANTABLE).add(ModItems.CRIMSONITE_BOOTS);
        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR_ENCHANTABLE).add(ModItems.CRIMSONITE_HELMET);
        getOrCreateTagBuilder(ItemTags.LEG_ARMOR_ENCHANTABLE).add(ModItems.CRIMSONITE_LEGGINGS);
    }
}
