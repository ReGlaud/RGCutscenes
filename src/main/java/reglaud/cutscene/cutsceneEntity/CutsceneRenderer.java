package reglaud.cutscene.cutsceneEntity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Quaterniond;
import org.joml.Vector3d;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CutsceneRenderer extends GeoEntityRenderer<CutsceneEntity> {

    public CutsceneRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CutsceneModel());
    }

    @Override
    public void render(CutsceneEntity entity, float entityYaw, float partialTick, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, entityYaw, partialTick, matrices, vertexConsumers, light);

        GeoBone bone = this.model.getBone("camera_point").orElse(null);

        if (bone == null) return;

        Vector3d bonePos = bone.getWorldPosition();
        Vec3d finalPos = new Vec3d(bonePos.x, bonePos.y  - 1.62, bonePos.z);

        Matrix4f matrix = new Matrix4f(bone.getWorldSpaceMatrix());
        Quaterniond rotation = matrix.getNormalizedRotation(new Quaterniond());
        Vector3d euler = rotation.getEulerAnglesYXZ(new Vector3d());

        double pitch = Math.toDegrees(euler.x);
        double yaw = Math.toDegrees(euler.y);
        double roll = Math.toDegrees(euler.z);

        Vec3d finalRot = new Vec3d(-yaw, pitch, roll);

        entity.setBoneData(new BoneData().setPosition(finalPos).setRotation(finalRot));

    }
}
