package net.cosmic.cdepth.entity.client;

import net.cosmic.cdepth.CDepth;
import net.cosmic.cdepth.entity.custom.CrimslinEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
public class CrimslinModel extends GeoModel<CrimslinEntity> {
    @Override public Identifier getModelResource(CrimslinEntity object) {
        return Identifier.of(CDepth.MOD_ID, "geo/crimslin.geo.json");
    }

    @Override public Identifier getTextureResource(CrimslinEntity object) {
        return Identifier.of(CDepth.MOD_ID, "textures/entity/crimslin.png");
    }

    @Override public Identifier getAnimationResource(CrimslinEntity crimslin) {
        return Identifier.of(CDepth.MOD_ID, "animations/crimslin.animation.json");
    }
}
