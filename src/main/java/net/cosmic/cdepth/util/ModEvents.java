package net.cosmic.cdepth.util;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.item.ModItems;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class ModEvents {
    public static void registerModEvents() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();
            ItemStack itemStack = player.getMainHandStack();
            if (world.getBlockState(pos).getBlock() == Blocks.LAVA_CAULDRON && itemStack.getItem() == Items.NETHERITE_SCRAP) {
                ItemStack newStack = new ItemStack(ModItems.HEATED_NETHERITE_SCRAP);
                newStack.setCount(itemStack.getCount());
                player.setStackInHand(Hand.MAIN_HAND,newStack);
                player.getWorld().playSound(null,hitResult.getBlockPos(), SoundEvents.ENTITY_GENERIC_BURN, SoundCategory.PLAYERS,0.5F,1.0F);
                player.getWorld().playSound(null,hitResult.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS,0.5F,1.0F);
                return ActionResult.SUCCESS;
            } else if (world.getBlockState(pos).getBlock() == Blocks.POWDER_SNOW_CAULDRON && itemStack.getItem() == ModItems.HEATED_NETHERITE_SCRAP) {
                ItemStack newStack = new ItemStack(Items.NETHERITE_SCRAP);
                newStack.setCount(itemStack.getCount());
                player.setStackInHand(Hand.MAIN_HAND,newStack);
                player.getWorld().playSound(null,hitResult.getBlockPos(), SoundEvents.ENTITY_GENERIC_BURN, SoundCategory.PLAYERS,0.5F,1.0F);
                player.getWorld().playSound(null,hitResult.getBlockPos(), SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.PLAYERS,0.5F,1.0F);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });
        CDepth.LOGGER.info("Registering Mod Events for mod: " + CDepth.MOD_ID);
    }
}
