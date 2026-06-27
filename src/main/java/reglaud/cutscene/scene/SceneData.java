package reglaud.cutscene.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneData {

    private final String TYPE;
    private final List<SceneStep> sceneSteps = new ArrayList<>();


    public SceneData(String type) {
        TYPE = type;
    }

    public void addStep(SceneStep sceneStep) {
        sceneSteps.add(sceneStep);
    }

    public String getType() {
        return TYPE;
    }

    public List<SceneStep> getAllSteps() {
        return sceneSteps;
    }

    public SceneStep getStep(int i) {
        return sceneSteps.get(i);
    }

}
