package reglaud.cutscene.client.events;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import reglaud.cutscene.SceneContollers.SceneController;
import reglaud.cutscene.SceneContollers.SceneManager;
import reglaud.cutscene.registry.SceneRegistry;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ClientCommand {

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    literal("reglaud").then(
                            literal("cutscene")
                                    // 1. Ветка START: /reglaud cutscene start <name>
                                    .then(literal("start")
                                            .then(argument("name", StringArgumentType.word())
                                                    .suggests((context, builder) -> {
                                                        for (String id : SceneRegistry.getRegisteredScenes().keySet()) {
                                                            builder.suggest(id);
                                                        }
                                                        return builder.buildFuture();
                                                    })
                                                    .executes(ctx -> {
                                                        String id = StringArgumentType.getString(ctx, "name");
                                                        try {
                                                            SceneController.startScene(id);
                                                        } catch (Exception e) {
                                                            ctx.getSource().sendError(net.minecraft.text.Text.of("Сцена не найдена " + id));
                                                        }
                                                        return 1;
                                                    })
                                            )
                                    )
                                    // 2. Ветка STOPALL: /reglaud cutscene stopAll
                                    .then(literal("stopAll")
                                            .executes(ctx -> {
                                                SceneController.stopAllScenes();
                                                return 1;
                                            })
                                    )
                    )
            );
        });
    }

}