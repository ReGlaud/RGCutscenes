package reglaud.cutscene.scene;

import reglaud.cutscene.api.ITickContext;
import reglaud.cutscene.api.IUpdateContext;

import java.util.Objects;
import java.util.function.Consumer;

public class SceneStep {

    public final int duration;
    public final boolean playAtOnce;
    private final Consumer<ITickContext> tick;
    private final Consumer<IUpdateContext> update;


    public SceneStep(int duration, boolean playAtOnce, Consumer<ITickContext> tick, Consumer<IUpdateContext> update) {
        if (duration < 1) {
            throw new IllegalArgumentException("Длительность шага должна быть больше 0! Передано: " + duration);
        }
        this.duration = duration;
        this.playAtOnce = playAtOnce;
        this.tick = Objects.requireNonNull(tick, "Логика 'tick' не может быть null! Используйте пустую лямбду: c -> {}");
        this.update = Objects.requireNonNull(update, "Логика 'update' не может быть null! Используйте пустую лямбду: c -> {}");
    }

    public void tick(ITickContext context) {
        this.tick.accept(context);
    }

    public void update(IUpdateContext context) {
        this.update.accept(context);
    }

}
