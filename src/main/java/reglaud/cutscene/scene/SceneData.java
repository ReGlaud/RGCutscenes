package reglaud.cutscene.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneData {

    private final String TYPE;
    private List<SceneSteps> sceneSteps = new ArrayList<>();


    public SceneData(String type) {
        TYPE = type;
    }

    public void addStep(SceneStep sceneStep) {
        SceneSteps.add(sceneStep);
    }

}
