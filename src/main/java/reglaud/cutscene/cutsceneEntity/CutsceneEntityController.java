package reglaud.cutscene.cutsceneEntity;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CutsceneEntityController {

    private static final Map<UUID, CutsceneEntity> CUTSCENES = new HashMap<>();

    public static void start(ServerWorld world, String type, Vec3d pos, float yaw, PlayerEntity player) {
        CutsceneEntity cutsceneEntity = new CutsceneEntity(ModEntities.CUTSCENE_ENTITY, world);

        cutsceneEntity.setCutsceneType(type);
        cutsceneEntity.setOwner(player.getUuid());
        cutsceneEntity.refreshPositionAndAngles(pos.x, pos.y, pos.z, yaw, 0);

        CUTSCENES.put(cutsceneEntity.getUuid(), cutsceneEntity);
        world.spawnEntity(cutsceneEntity);
    }

    public static void stop(UUID cutscene) {
        if ( CUTSCENES.get(cutscene) != null ) {
            CUTSCENES.get(cutscene).discard();
            CUTSCENES.remove(cutscene);
        }
    }

    // Client
    public static void register() {
        EntityRendererRegistry.register(ModEntities.CUTSCENE_ENTITY, CutsceneRenderer::new);
    }

}
