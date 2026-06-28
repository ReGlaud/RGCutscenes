package reglaud.cutscene.client.events;

import reglaud.cutscene.SceneContollers.SceneController;

public class ClientTickEvents {

    public static void register() {

        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null || client.world == null) return;

            SceneController.tickAll();

        });

    }

}
