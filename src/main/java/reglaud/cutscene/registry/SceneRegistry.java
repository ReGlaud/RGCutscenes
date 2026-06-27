package reglaud.cutscene.registry;

import reglaud.cutscene.api.ISetType;

public class SceneRegistry {

    public static ISetType register() {
        return new Builder();
    }

}
