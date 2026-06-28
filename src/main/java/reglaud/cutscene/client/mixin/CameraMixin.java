package reglaud.cutscene.client.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import reglaud.cutscene.cutsceneEntity.CustomCamera; // Твой класс камеры

@Mixin(Camera.class)
public abstract class CameraMixin {

    @Shadow protected abstract void setPostion(double x, double y, double z);
    @Shadow protected abstract void setRotation(float yaw, float pitch);

    @Inject(method = "update", at = @At("RETURN"))
    private void overrideCameraWithCutscene(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {

        if (CustomCamera.connected) {
            CustomCamera.update();
        }

        if (CustomCamera.connected && CustomCamera.pos != null) {
            this.setPostion(CustomCamera.pos.x, CustomCamera.pos.y, CustomCamera.pos.z);
            this.setRotation(CustomCamera.yaw, CustomCamera.pitch);
        }
    }
}