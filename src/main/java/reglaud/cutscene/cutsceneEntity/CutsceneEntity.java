package reglaud.cutscene.cutsceneEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;
import java.util.UUID;

public class CutsceneEntity extends Entity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean started = false;

    public BoneData boneData;
    public boolean cameraReady = false;
    public boolean haveOwner = false;

    // ===================== LOGIC =====================

    public void nowItHaveOwner() {
        haveOwner = true;
    }

    public void setBoneData(BoneData boneData) {
        this.boneData = boneData;
        cameraReady = true;
    }

    public BoneData getBoneData() {
        return boneData;
    }

    // ===================== DATA TRACKER =====================

    private static final TrackedData<String> TYPE =
            DataTracker.registerData(CutsceneEntity.class, TrackedDataHandlerRegistry.STRING);

    private static final TrackedData<Optional<UUID>> OWNER =
            DataTracker.registerData(CutsceneEntity.class, TrackedDataHandlerRegistry.OPTIONAL_UUID);

    public CutsceneEntity(EntityType<? extends CutsceneEntity> type, World world) {
        super(type, world);
    }

    // ===================== TYPE =====================

    public void setCutsceneType(String type) {
        this.dataTracker.set(TYPE, type);
    }

    public String getCutsceneType() {
        return this.dataTracker.get(TYPE);
    }

    // ===================== OWNER =====================

    public void setOwner(UUID owner) {
        this.dataTracker.set(OWNER, Optional.of(owner));
    }

    public UUID getOwner() {
        return this.dataTracker.get(OWNER).orElse(null);
    }

    // ===================== DATA TRACKER INIT =====================

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(TYPE, "");
        this.dataTracker.startTracking(OWNER, Optional.empty());
    }

    // ===================== NBT =====================

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putString("type", this.dataTracker.get(TYPE));
        UUID owner = getOwner();
        if (owner != null) {
            nbt.putUuid("owner", owner);
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("type")) {
            this.dataTracker.set(TYPE, nbt.getString("type"));
        }

        if (nbt.containsUuid("owner")) {
            this.dataTracker.set(OWNER, Optional.of(nbt.getUuid("owner")));
        }
    }

    // ===================== ENTITY BEHAVIOR =====================

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public boolean shouldRender(double distance) {
        return true;
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {return true;}

    // ===================== GECKOLIB =====================

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, state -> {
            if (!started) {
                started = true;
                state.setAnimation(RawAnimation.begin().then("animation", Animation.LoopType.PLAY_ONCE));
                return PlayState.CONTINUE;
            }

            return PlayState.STOP;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}