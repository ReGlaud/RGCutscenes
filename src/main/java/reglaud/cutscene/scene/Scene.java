package reglaud.cutscene.scene;

import net.minecraft.client.gui.DrawContext;
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
    private float smoothLocalTime;
    private int globalTime = -1;
    private int nextStepTime = 0;
    private DrawContext drawContext;


    public Scene(SceneData sceneData) {
        this.ID = sceneData.getID();
        this.sceneSteps = sceneData.getAllSteps();
        this.haveEntity = sceneData.getHaveEntity();
    }

    public void tick() {
        globalTime += 1;
        localTime += 1;

        if (globalTime == nextStepTime) {
            if (currentStepIndex == sceneSteps.size() - 1) {
                deleteScene();
                return;
            }
            localTime = 0;
            currentStepIndex += 1;
            currentStep = sceneSteps.get(currentStepIndex);
            nextStepTime = globalTime + currentStep.duration;
        }

        currentStep.tick(this);
    }

    public void update(float tickDelta, DrawContext drawContext) {
        smoothLocalTime = localTime + tickDelta;
        this.drawContext = drawContext;
        currentStep.update(this);
    }

}
