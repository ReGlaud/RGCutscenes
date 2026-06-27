package reglaud.cutscene.registry;

import reglaud.cutscene.api.ISetType;
import reglaud.cutscene.scene.SceneData;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SceneRegistry {

    private static final Map<String, SceneData> REGISTERED_SCENES = new ConcurrentHashMap<>();

    public static ISetType register() {
        return new Builder();
    }

    public static void putNewSceneData(SceneData data) {
        String id = data.getID();
        REGISTERED_SCENES.put(id, data);
    }

    public static SceneData getScene(String id) {
        return REGISTERED_SCENES.get(id);
    }

}
