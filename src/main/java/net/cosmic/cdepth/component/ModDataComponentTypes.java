package net.cosmic.cdepth.component;

import com.mojang.serialization.Codec;
import net.cosmic.cdepth.CDepth;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<Integer> TOGGLE_CRIMSLIN = register("toggle_crimslin",
            builder -> builder.codec(Codec.INT).packetCodec(PacketCodecs.VAR_INT));
    public static final ComponentType<Integer> CRIMSON_COMBO = register("crimson_combo",
            builder -> builder.codec(Codec.INT).packetCodec(PacketCodecs.VAR_INT));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(CDepth.MOD_ID,name),
                builderOperator.apply(ComponentType.builder()).build());}
    public static void registerModDataComponentTypes() {CDepth.LOGGER.info("Registering Mod Data Component Types for mod: " + CDepth.MOD_ID);}
}
