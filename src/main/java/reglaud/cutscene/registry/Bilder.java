package reglaud.cutscene.registry;

import reglaud.cutscene.api.IAddStep;
import reglaud.cutscene.api.ISetType;
import reglaud.cutscene.scene.CutsceneData;

public class Builder implements ISetType, IAddStep {

    CutsceneData cutsceneData;

    @Override
    public IAddStep setType(String type){
        cutsceneData = new CutsceneData(type);
        return this;
    }

    @Override
    public IAddStep addStep(CutsceneStep step) {
        cutsceneData.addStep(step);
        return this;
    }

    @Override
    public Scene build() {
        // В разработке
    }

}
