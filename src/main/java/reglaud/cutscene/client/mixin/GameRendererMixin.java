package reglaud.cutscene.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import reglaud.cutscene.cutsceneEntity.CustomCamera;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "renderWorld", at = @At("HEAD"))
    private void onRenderWorldStart(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo ci) {

        if (CustomCamera.connected) {

            CustomCamera.update();

            float roll = CustomCamera.roll;

                if (roll != 0) {
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(roll));
                }

        }
    }

    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    private void modifyCutsceneFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Double> cir) {
        if (CustomCamera.connected) {
            cir.setReturnValue(70.0);
        }
    }

}
