package reglaud.cutscene.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneData {

    private final String ID;
    private final List<SceneStep> sceneSteps = new ArrayList<>();
    private final boolean haveEntity;
    private boolean frozen = false;


    public SceneData(String id, boolean haveEntity) {
        this.ID = id;
        this.haveEntity = haveEntity;
    }

    public void addStep(SceneStep sceneStep) {
        if (frozen) {
            throw new IllegalStateException("Нельзя добавлять шаги в SceneData после того, как был вызван build()!");
        }
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

    public void freeze() {
        this.frozen = true;
    }

}
