package reglaud.cutscene.client;

import net.fabricmc.api.ClientModInitializer;
import reglaud.cutscene.client.events.*;

public class RGCutsceneClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientTickEvents.register();
		ClientCommand.register();
		HUDRenderCallback.register();
	}
}