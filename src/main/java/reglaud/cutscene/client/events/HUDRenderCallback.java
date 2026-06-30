package reglaud.cutscene.client.events;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import reglaud.cutscene.sceneControllers.SceneController;

public class HUDRenderCallback {

    public static void register() {

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {

            MinecraftClient client = MinecraftClient.getInstance();

            if (client.player == null || client.world == null) return;

            SceneController.updateAll(tickDelta, drawContext);

        });

    }

}
