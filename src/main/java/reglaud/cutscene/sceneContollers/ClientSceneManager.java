package reglaud.cutscene.sceneContollers;

import reglaud.cutscene.registry.SceneRegistry;
import reglaud.cutscene.scene.Scene;
import reglaud.cutscene.scene.SceneData;

public class ClientSceneManager {

    public static Scene startScene(SceneData sceneData) {
        Scene scene = new Scene(sceneData);
        SceneController.addScene(scene);
        return scene;
    }

    public static void startScene(String id) {
        SceneData sceneData = SceneRegistry.getScene(id);
        if (sceneData == null) {
            throw new IllegalArgumentException("Сцены, имеющей id " + id + " не существует!");
        }
        Scene scene = new Scene(sceneData);
        SceneController.addScene(scene);
    }

    public static void stopScene(Scene scene) {
        scene.deleteScene();
        SceneController.deleteScene(scene);
    }

    public static void stopAllScenes() {
        for (Scene scene : SceneController.getActiveScenes()) {
            scene.deleteScene();
            SceneController.deleteAllScenes();
        }
    }

}
