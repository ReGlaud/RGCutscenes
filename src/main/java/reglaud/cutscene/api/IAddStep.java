package reglaud.cutscene.api;

import reglaud.cutscene.scene.SceneData;

import java.util.function.Consumer;

public interface IAddStep {
    IAddStep addStep(int duration, boolean playAtOnce, Consumer<ITickContext> tick, Consumer<IUpdateContext> update);
    SceneData build();
}
