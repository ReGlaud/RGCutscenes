package reglaud.cutscene.test;

import net.minecraft.client.MinecraftClient;
import reglaud.cutscene.registry.SceneRegistry;
import reglaud.cutscene.scene.SceneData;

public class Test {

    public static SceneData FADE_SCENE;


    public static void register() {
        FADE_SCENE = SceneRegistry.register()
                .setType("fade_test", false) // ID сцены, haveEntity = false

                // --- ШАГ 1: Плавное затемнение (10 тиков) ---
                .addStep(10, false,
                        tickCtx -> {
                            // В тиках логика не нужна, работаем только в рендере
                        },
                        renderCtx -> {
                            // Получаем плавное время шага (от 0.0f до 10.0f)
                            float time = renderCtx.getSmoothLocalTime();
                            // Рассчитываем альфа-канал от 0.0 до 1.0
                            float alpha = Math.min(time / 10f, 1.0f);

                            drawBlackScreen(renderCtx.getDrawContext(), alpha);
                        }
                )

                // --- ШАГ 2: Полная темнота (20 тиков) ---
                .addStep(20, false,
                        tickCtx -> {},
                        renderCtx -> {
                            // Просто рисуем глухой черный экран (alpha = 1.0)
                            drawBlackScreen(renderCtx.getDrawContext(), 1.0f);
                        }
                )

                // --- ШАГ 3: Плавное проявление (10 тиков) ---
                .addStep(10, false,
                        tickCtx -> {},
                        renderCtx -> {
                            float time = renderCtx.getSmoothLocalTime();
                            // Рассчитываем альфа-канал в обратную сторону: от 1.0 до 0.0
                            float alpha = Math.max(1.0f - (time / 10f), 0.0f);

                            drawBlackScreen(renderCtx.getDrawContext(), alpha);
                        }
                )
                .build(); // Сцена автоматически улетает в твой SceneRegistry!
    }

    /**
     * Вспомогательный метод для отрисовки черного прямоугольника на весь экран
     */
    private static void drawBlackScreen(net.minecraft.client.gui.DrawContext context, float alpha) {
        MinecraftClient client = MinecraftClient.getInstance();
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        // Переводим float альфу (0.0 - 1.0) в байтовое значение (0 - 255)
        int a = (int) (alpha * 255f);
        // Собираем ARGB цвет: черный цвет (0x000000) с нашей альфой, сдвинутой в старшие байты
        int color = (a << 24);

        // Рисуем оверлей на весь экран
        context.fill(0, 0, width, height, color);
    }
}
