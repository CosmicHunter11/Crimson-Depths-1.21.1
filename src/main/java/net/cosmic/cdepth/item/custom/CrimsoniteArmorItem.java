package net.cosmic.cdepth.item.custom;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.cosmic.cdepth.entity.client.armor.CrimsoniteArmorRenderer;
import net.cosmic.cdepth.item.ModItems;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Set;
import java.util.function.Consumer;


public class CrimsoniteArmorItem extends ArmorItem implements GeoItem {
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("idle");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public CrimsoniteArmorItem(Type type, Settings settings) {
        super(ArmorMaterials.NETHERITE, type, settings);
    }

    // MISC //
    @Override public boolean hasGlint(ItemStack stack) {return false;}
    @Override public int getEnchantability() {return 15;}
    @Override public boolean isEnchantable(ItemStack stack) {return true;}

    @Override public AnimatableInstanceCache getAnimatableInstanceCache() {return this.cache;}
    public int getArmorValue(PlayerEntity user) {
        int value = 1;
        if(user.getInventory().getArmorStack(0).getItem() instanceof CrimsoniteArmorItem){value++;}
        if(user.getInventory().getArmorStack(1).getItem() instanceof CrimsoniteArmorItem){value++;}
        if(user.getInventory().getArmorStack(2).getItem() instanceof CrimsoniteArmorItem){value++;}
        if(user.getInventory().getArmorStack(3).getItem() instanceof CrimsoniteArmorItem){value++;} return value;}

    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, 20, state -> {
            state.getController().setAnimation(IDLE_ANIM);
            Entity entity = state.getData(DataTickets.ENTITY);
            if (entity instanceof ArmorStandEntity)
                return PlayState.CONTINUE;

            if (entity instanceof LivingEntity livingEntity) {
                Set<Item> wornArmor = new ObjectOpenHashSet<>();
                for (ItemStack stack : livingEntity.getArmorItems()) {
                    if (stack.isEmpty()) return PlayState.STOP;
                    wornArmor.add(stack.getItem());
                }
                boolean isFullSet = wornArmor.containsAll(ObjectArrayList.of(
                        ModItems.CRIMSONITE_HELMET,ModItems.CRIMSONITE_CHESTPLATE
                ,ModItems.CRIMSONITE_LEGGINGS,ModItems.CRIMSONITE_BOOTS));

                return isFullSet ? PlayState.CONTINUE : PlayState.STOP;
            } return PlayState.STOP;
        }));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity user && getArmorValue(user) > 2) {
            if (user.getFireTicks() > 65) {user.setFireTicks(65);}
            if (user.getFrozenTicks() > 200) {user.setFrozenTicks(200);}
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    // RENDERER //
    @Override public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private GeoArmorRenderer<?> renderer;
            @Override public <T extends LivingEntity> @NotNull BipedEntityModel<?> getGeoArmorRenderer(@Nullable T livingEntity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot, @Nullable BipedEntityModel<T> original) {
                if (this.renderer == null) {this.renderer = new CrimsoniteArmorRenderer();}return this.renderer;}});
    }
}
