package net.cosmic.cdepth.entity.client.armor;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.item.custom.CrimsoniteArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CrimsoniteArmorModel extends GeoModel<CrimsoniteArmorItem> {
    @Override
    public Identifier getModelResource(CrimsoniteArmorItem object) {return Identifier.of(
            CDepth.MOD_ID,"geo/crimsonite_armor.geo.json");}

    @Override
    public Identifier getTextureResource(CrimsoniteArmorItem object) {return Identifier.of(
            CDepth.MOD_ID,"textures/models/armor/crimsonite_armor_texture.png");}

    @Override
    public Identifier getAnimationResource(CrimsoniteArmorItem object) {return Identifier.of(
            CDepth.MOD_ID,"animations/armor_animation.json");}
}
