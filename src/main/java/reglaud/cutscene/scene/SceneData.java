package reglaud.cutscene.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneData {

    private final String ID;
    private final List<SceneStep> sceneSteps = new ArrayList<>();
    private final boolean haveEntity;


    public SceneData(String id, boolean haveEntity) {
        this.ID = id;
        this.haveEntity = haveEntity;
    }

    public void addStep(SceneStep sceneStep) {
        sceneSteps.add(sceneStep);
    }

    public String getID() {
        return ID;
    }

    public List<SceneStep> getAllSteps() {
        return sceneSteps;
    }

    public boolean getHaveEntity() {
        return haveEntity;
    }

}
