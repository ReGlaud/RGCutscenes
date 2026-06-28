package reglaud.cutscene.SceneContollers;

import net.minecraft.client.gui.DrawContext;
import reglaud.cutscene.scene.Scene;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class SceneController {

    public final static List<Scene> activeScenes = new CopyOnWriteArrayList<>();

    public static void addScene(Scene scene) {
        activeScenes.add(scene);
    }

    public static void deleteScene(Scene scene) {
        activeScenes.remove(scene);
    }

    public static void deleteAllScenes() {
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

    public static List<Scene> getActiveScenes() {
        return activeScenes;
    }

}
