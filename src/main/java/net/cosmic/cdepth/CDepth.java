package net.cosmic.cdepth;

import net.cosmic.cdepth.block.ModBlocks;
import net.cosmic.cdepth.component.ModDataComponentTypes;
import net.cosmic.cdepth.enchantment.ModEnchantmentEffects;
import net.cosmic.cdepth.enchantment.ModEnchantments;
import net.cosmic.cdepth.entity.ModEntities;
import net.cosmic.cdepth.item.ModItemGroups;
import net.cosmic.cdepth.item.ModItems;
import net.cosmic.cdepth.sound.ModSounds;
import net.cosmic.cdepth.particle.ModParticles;
import net.cosmic.cdepth.util.ModEvents;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CDepth implements ModInitializer {
	public static final String MOD_ID = "cdepth";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerModSounds();
		ModEvents.registerModEvents();
		ModEntities.registerModEntities();
		ModParticles.registerModParticles();
		ModItemGroups.registerModItemGroups();
		ModEnchantments.registerModEnchantments();
		ModEnchantmentEffects.registerModEnchantmentEffects();
		ModDataComponentTypes.registerModDataComponentTypes();
		LOGGER.info("Initialized Crimson Depths - 1.21.1");
	}
}