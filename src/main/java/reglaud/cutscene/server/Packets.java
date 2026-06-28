package reglaud.cutscene.server;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import reglaud.cutscene.cutsceneEntity.CustomCamera;
import reglaud.cutscene.cutsceneEntity.CutsceneEntity;
import reglaud.cutscene.sceneContollers.ClientSceneManager;
import reglaud.cutscene.sceneContollers.SceneController;

import java.util.UUID;

public class Packets {

    // Переводим ID пакетов в нижний регистр по стандартам Майнкрафта
    public static final Identifier START_CUTSCENE_S2C = new Identifier("rgcutscenes", "start_cutscene");
    public static final Identifier STOP_CUTSCENE_S2C = new Identifier("rgcutscenes", "stop_cutscene");

    // ==============================================================================================
    //                                СЕРВЕРНАЯ ЧАСТЬ (ОТПРАВКА)
    // ==============================================================================================


    public static void sendStartToClient(ServerPlayerEntity player, String sceneId, UUID entityUuid) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(sceneId);   // Передаем строковый ID новой архитектуры
        buf.writeUuid(entityUuid);  // Передаем UUID сущности-актера для привязки камеры

        // Отправляем строго этому игроку с сервера на его клиент
        ServerPlayNetworking.send(player, START_CUTSCENE_S2C, buf);
    }


    public static void sendStopToClient(ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        ServerPlayNetworking.send(player, STOP_CUTSCENE_S2C, buf);
    }

    // ==============================================================================================
    //                                КЛИЕНТСКАЯ ЧАСТЬ (ПРИЕМНИКИ)
    // ==============================================================================================

    /**
     * Регистрируется СТРОГО на клиенте (например, в RGCutsceneClient)
     */
    public static void registerClientReceivers() {
        // Приемник старта кат-сцены
        ClientPlayNetworking.registerGlobalReceiver(START_CUTSCENE_S2C, (client, handler, buf, responseSender) -> {
            String sceneId = buf.readString();
            UUID entityUuid = buf.readUuid();

            // Перенаправляем выполнение в основной поток клиента Майнкрафта, чтобы избежать крашей
            client.execute(() -> {
                // 1. Запускаем плеер сцены на клиенте по её ID из реестра
                ClientSceneManager.startScene(sceneId);

                for (net.minecraft.entity.Entity e : client.world.getEntities()) {
                    if (e.getUuid().equals(entityUuid) && e instanceof CutsceneEntity cutsceneActor) {

                        SceneController.putCutsceneEntity(sceneId, cutsceneActor);

                        break; // Нашли, дальше искать не нужно
                    }
                }
            });
        });

        // Приемник принудительной остановки
        ClientPlayNetworking.registerGlobalReceiver(STOP_CUTSCENE_S2C, (client, handler, buf, responseSender) -> {
            client.execute(() -> {
                ClientSceneManager.stopAllScenes(); // Вычищаем плеер на клиенте
            });
        });
    }
}