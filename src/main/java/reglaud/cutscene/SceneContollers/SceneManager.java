package reglaud.cutscene.SceneContollers;

import net.minecraft.client.gui.DrawContext;
import reglaud.cutscene.registry.SceneRegistry;
import reglaud.cutscene.scene.Scene;
import reglaud.cutscene.scene.SceneData;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SceneManager {

    private final static List<Scene> activeScenes = new CopyOnWriteArrayList<>();

    private static void addScene(Scene scene) {
        activeScenes.add(scene);
    }

    private static void deleteScene(Scene scene) {
        activeScenes.remove(scene);
    }

    private static void deleteAllScenes() {
        activeScenes.clear();
    }

    public static void tickAll() {
        for(Scene scene : activeScenes) {
            scene.tick();
        }
    }

    public static void updateAll(float tickDelta, DrawContext drawContext) {
        for (Scene scene : activeScenes) {
            scene.update(tickDelta, drawContext);
        }
    }

    public static Scene startScene(SceneData sceneData) {
        Scene scene = new Scene(sceneData);
        addScene(scene);
        return scene;
    }

    public static void startScene(String id) {
        SceneData sceneData = SceneRegistry.getScene(id);
        if (sceneData == null) {
            throw new IllegalArgumentException("Сцены, имеющей id " + id + " не существует!");
        }
        Scene scene = new Scene(sceneData);
        addScene(scene);
    }

    public static void stopScene(Scene scene) {
        scene.deleteScene();
        deleteScene(scene);
    }

    public static void stopAllScenes() {
        for (Scene scene : activeScenes) {
            scene.deleteScene();
            deleteAllScenes();
        }
    }

}
