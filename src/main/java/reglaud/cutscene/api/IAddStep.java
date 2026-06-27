package reglaud.cutscene.api;

import java.util.function.Consumer;

public interface IAddStep {
    IAddStep addStep(int duration, boolean playAtOnce, Consumer<Something> tick, Consumer<Something> update);
    Scene build();
}
