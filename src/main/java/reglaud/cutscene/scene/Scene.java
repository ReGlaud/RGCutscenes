package reglaud.cutscene.scene;

import net.minecraft.client.gui.DrawContext;
import reglaud.cutscene.SceneContollers.SceneManager;
import reglaud.cutscene.api.ITickContext;
import reglaud.cutscene.api.IUpdateContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Map<String, Object> memory = new HashMap<>();
    private boolean alive = true;

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
                alive = false;
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
        if (this.currentStep == null) return;
        smoothLocalTime = localTime + tickDelta;
        this.drawContext = drawContext;
        currentStep.update(this);
    }

    @Override
    public void deleteScene() {}

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void memoryAdd(String key, Object object) {
        if (memory.containsKey(key)) {
            throw new IllegalStateException("Нельзя добавить переменную с ключом '" + key + "', так как она уже существует. Для изменения переменной используйте memoryChange!");
        }
        memory.put(key, object);
    }

    @Override
    public Object memoryGet(String key) {
        return memory.get(key);
    }

    @Override
    public void memoryChange(String key, Object object) {
        if (!memory.containsKey(key)) {
            throw new IllegalStateException("Нельзя изменить переменную с ключом '" + key + "', так как она еще не была добавлена через memoryAdd!");
        }
        memory.put(key, object);
    }

    @Override
    public float getSmoothLocalTime() {
        return this.smoothLocalTime; // Твоя переменная плавного времени
    }

    @Override
    public DrawContext getDrawContext() {
        return this.drawContext; // Твое поле DrawContext, которое ты сохраняешь в update()
    }

}
