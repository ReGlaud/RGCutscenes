package reglaud.cutscene.cutsceneEntity;

import net.minecraft.util.math.Vec3d;

public class BoneData {

    private Vec3d bonePos;
    private Vec3d boneRot;

    public BoneData setPosition(double x, double y, double z) {
        bonePos = new Vec3d(x, y, z);
        return this;
    }

    public BoneData setPosition(Vec3d pos) {
        bonePos = pos;
        return this;
    }

    public BoneData setRotation(double x, double y, double z) {
        boneRot = new Vec3d(x, y, z);
        return this;
    }

    public BoneData setRotation(Vec3d rot) {
        boneRot = rot;
        return this;
    }

    public Vec3d getBonePos() {
        return bonePos;
    }

    public Vec3d getBoneRot() {
        return boneRot;
    }

}

