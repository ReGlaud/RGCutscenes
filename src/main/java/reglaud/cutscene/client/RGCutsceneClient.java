package reglaud.cutscene.client;

import net.fabricmc.api.ClientModInitializer;
import reglaud.cutscene.client.events.*;
import reglaud.cutscene.cutsceneEntity.CutsceneEntityController;

public class RGCutsceneClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientTickEvents.register();
		ClientCommand.register();
		HUDRenderCallback.register();
		CutsceneEntityController.register();
	}
}