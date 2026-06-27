package reglaud.cutscene.scene;

import reglaud.cutscene.api.ITickContext;
import reglaud.cutscene.api.IUpdateContext;

import java.util.List;

public class Scene implements ITickContext, IUpdateContext {

    private final String ID;
    private final List<SceneStep> sceneSteps;
    private final boolean haveEntity;
    private int currentStepIndex = -1;
    private SceneStep currentStep;
    private int localTime;
    private int globalTime = 0;
    private int nextStepTime = 0;


    public Scene(SceneData sceneData) {
        this.ID = sceneData.getID();
        this.sceneSteps = sceneData.getAllSteps();
        this.haveEntity = sceneData.getHaveEntity();
    }

    public void tick() {
        globalTime += 1;

        if (globalTime > nextStepTime) {
            localTime = 0;
            currentStepIndex += 1;
            currentStep = sceneSteps.get(currentStepIndex);
            nextStepTime = globalTime + currentStep.duration -1;
        }

        localTime += 1;

        currentStep.tick(this);
    }

}
