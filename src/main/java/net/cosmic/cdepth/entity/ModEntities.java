package net.cosmic.cdepth.entity;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.entity.custom.CrimslinEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<CrimslinEntity> CRIMSLIN = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(CDepth.MOD_ID,"crimslin"), EntityType.Builder.create(CrimslinEntity::new,
                    SpawnGroup.CREATURE).dimensions(0.6F,1.0F).build());

    public static void registerModEntities() {
        FabricDefaultAttributeRegistry.register(ModEntities.CRIMSLIN,
                CrimslinEntity.setAttributes());
        CDepth.LOGGER.info("Registering Mod Entities for mod: " + CDepth.MOD_ID);
    }
}
