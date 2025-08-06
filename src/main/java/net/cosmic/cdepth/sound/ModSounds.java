package net.cosmic.cdepth.sound;

import net.cosmic.cdepth.CDepth;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static SoundEvent CRIMSON_SLICE = registerSoundEvent("crimson_slice");
    public static SoundEvent CRIMSON_CRIT = registerSoundEvent("crimsonite_longsword_crit");
    public static SoundEvent CRIMSON_CRIT_HEAVY = registerSoundEvent("crimsonite_longsword_crit_heavy");
    public static SoundEvent CRIMSONITE_CAST = registerSoundEvent("crimsonite_cast");
    public static SoundEvent CRIMSONITE_LAUNCHER_CHARGE = registerSoundEvent("crimsonite_launcher_charges");
    public static SoundEvent CRIMSLIN_IDLE = registerSoundEvent("crimslin_idle");
    public static SoundEvent CRIMSLIN_HURT = registerSoundEvent("crimslin_hurt");
    public static SoundEvent CRIMSLIN_DIED = registerSoundEvent("crimslin_died");
    public static SoundEvent PARASOUL_DASH = registerSoundEvent("parasoul_dash");
    public static SoundEvent CRIMSON_EXPLOSION = registerSoundEvent("crimson_explosion");
    public static SoundEvent FRAUD_PLUSHIE_SQUEAK = registerSoundEvent("fraud_plushie_squeak");

    private static SoundEvent registerSoundEvent(String name) {Identifier id = Identifier.of(CDepth.MOD_ID,name);return Registry.register(Registries.SOUND_EVENT,id, SoundEvent.of(id));}
    public static void registerModSounds() {CDepth.LOGGER.info("Registering Mod Sounds for mod: " + CDepth.MOD_ID);}
}
