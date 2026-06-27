package reglaud.cutscene.registry;

import reglaud.cutscene.api.ISetType;

public class CutsceneRegistry {

    public static ISetType register() {
        return new Builder();
    }

}
