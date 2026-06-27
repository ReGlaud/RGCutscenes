package reglaud.cutscene.scene;

import java.util.Objects;
import java.util.function.Consumer;

public class SceneStep {

    public final int duration;
    public final boolean playAtOnce;
    private final Consumer<Something> tick;
    private final Consumer<Something> update;


    public SceneStep(int duration, boolean playAtOnce, Consumer<Something> tick, Consumer<Something> update) {
        if (duration < 1) {
            throw new IllegalArgumentException("Длительность шага должна быть больше 0! Передано: " + duration);
        }
        this.duration = duration;
        this.playAtOnce = playAtOnce;
        this.tick = Objects.requireNonNull(tick, "Логика 'tick' не может быть null! Используйте пустую лямбду: c -> {}");
        this.update = Objects.requireNonNull(update, "Логика 'update' не может быть null! Используйте пустую лямбду: c -> {}");
    }

    public void tick(Something context) {
        this.tick.accept(context);
    }

    public void update(Something context) {
        this.update.accept(context);
    }

    // Something = пока нет такого/таких класов

}
