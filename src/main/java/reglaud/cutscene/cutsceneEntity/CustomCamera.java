package reglaud.cutscene.cutsceneEntity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class CustomCamera {

    public static Vec3d pos;
    public static float yaw = 0.0f;
    public static float pitch = 0.0f;
    public static float roll = 0.0f;
    public static boolean isFree = true;
    public static boolean connected = false;
    public static CutsceneEntity cutscene;


    public static void start() {
        if (CustomCamera.isFree) {
            Vec3d playerPos = MinecraftClient.getInstance().player.getPos();
            CustomCamera.setPos(playerPos);
            CustomCamera.setPos(CustomCamera.pos.add(0, 1.5, 0));
            CustomCamera.isFree = false;
        } else {
            throw new RuntimeException("Невозможно запустить 2 разные катсцены, использующие камеру. Перед запуском убедитесь что камера свободна или принудительно завершите выполнение других катсцен, использующих камеру!");
        }
    }

    public static void stop() {
        if (!CustomCamera.isFree) {
            CustomCamera.pos = null;
            CustomCamera.roll = 0;
            CustomCamera.disconnect();
            CustomCamera.isFree = true;
            cutscene = null;
        }
    }

    public static void connect() {
        if (cutscene == null) {
            throw new RuntimeException("Для включения POV катсцены необходимо сначала задать катсцену спомощью putCutsceneEntity(cutscene). Если эта ошибка не пропадает добавьте небольшую паузу (3-5 кадров) между появлением энтити и привязкой.");
        }
        CustomCamera.connected = true;
    }

    public static void disconnect() {
        CustomCamera.connected = false;
    }

    public static void putCutsceneEntity(CutsceneEntity animation) {
        if (cutscene == null) {
            cutscene = animation;
        }
    }

    public static void setPos(Vec3d position) {
        CustomCamera.pos = position;
    }

    public static void setRot(Vec3d rot) {
        CustomCamera.yaw = (float)rot.x;
        CustomCamera.pitch = (float)rot.y;
        CustomCamera.roll = (float)rot.z;
    }

    public static void setRot(float yaw, float pitch, float roll) {
        CustomCamera.yaw = yaw;
        CustomCamera.pitch = pitch;
        CustomCamera.roll = roll;
    }

    public static void update() {
        if (connected && cutscene != null && cutscene.cameraReady) {
            BoneData boneData = cutscene.getBoneData();

            setPos(boneData.getBonePos());
            setRot(boneData.getBoneRot());

            cutscene.cameraReady = false;
        }
    }
}