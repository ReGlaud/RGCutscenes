package reglaud.cutscene.scene;

import java.util.ArrayList;
import java.util.List;

public class CutsceneData {

    private final String TYPE;
    private List<CutsceneSteps> cutsceneSteps = new ArrayList<>();


    public CutsceneData(String type) {
        TYPE = type;
    }

    public void addStep(CutsceneSteps cutsceneStep) {
        cutsceneSteps.add(cutsceneStep);
    }

}
