package reglaud.cutscene.registry;

import reglaud.cutscene.api.ISetType;
import reglaud.cutscene.api.IAddStep;
import reglaud.cutscene.api.ITickContext;
import reglaud.cutscene.api.IUpdateContext;
import reglaud.cutscene.scene.SceneData;
import reglaud.cutscene.scene.SceneStep;

import java.util.function.Consumer;

public class Builder implements ISetType, IAddStep {

    SceneData sceneData;

    @Override
    public IAddStep setType(String id, boolean haveEntity){
        sceneData = new SceneData(id, haveEntity);
        return this;
    }

    @Override
    public IAddStep addStep(int duration, boolean playAtOnce, Consumer<ITickContext> tick, Consumer<IUpdateContext> update) {
        sceneData.addStep(new SceneStep(duration, playAtOnce, tick, update));
        return this;
    }

    @Override
    public SceneData build() {
        return sceneData;
    }

}
