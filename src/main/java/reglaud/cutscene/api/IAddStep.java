package reglaud.cutscene.api;

public interface IAddStep {
    IAddStep addStep(CutsceneStep step);
    Scene build();
}
