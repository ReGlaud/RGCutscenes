package reglaud.cutscene.SceneContollers;

import reglaud.cutscene.SceneContollers.SceneManager;
import reglaud.cutscene.registry.SceneRegistry;
import reglaud.cutscene.scene.Scene;
import reglaud.cutscene.scene.SceneData;



public class SceneController {

    public static Scene startScene(SceneData sceneData) {
        Scene scene = new Scene(sceneData);
        SceneManager.addScene(scene);
        return scene;
    }

    public static void startScene(String id) {
        SceneData sceneData = SceneRegistry.getScene(id);
        if (sceneData == null) {
            throw new IllegalArgumentException("Сцены, имеющей id " + id + " не существует!");
        }
        Scene scene = new Scene(sceneData);
        SceneManager.addScene(scene);
    }

    public static void stopScene(Scene scene) {
        scene.deleteScene();
        SceneManager.deleteScene(scene);
    }

    public static void stopAllScenes() {
        for (Scene scene : SceneManager.getActiveScenes()) {
            scene.deleteScene();
            SceneManager.deleteAllScenes();
        }
    }

}
