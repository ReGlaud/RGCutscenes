package reglaud.cutscene.cutsceneEntity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CutsceneModel extends GeoModel<CutsceneEntity> {

    @Override
    public Identifier getModelResource(CutsceneEntity entity) {
        String path = "geo/" + entity.getCutsceneType() + ".geo.json";
        return new Identifier("rgcutscenes", path);
    }

    @Override
    public Identifier getTextureResource(CutsceneEntity entity) {
        String path = "textures/entity/" + entity.getCutsceneType() + "_texture.png";
        return new Identifier("rgcutscenes", path);
    }

    @Override
    public Identifier getAnimationResource(CutsceneEntity entity) {
        String path = "animations/" + entity.getCutsceneType() + ".animation.json";
        return new Identifier("rgcutscenes", path);
    }

}