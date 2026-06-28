package reglaud.cutscene.cutsceneEntity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<CutsceneEntity> CUTSCENE_ENTITY =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier("rg", "cutscene_entity"),
                    FabricEntityTypeBuilder.create(SpawnGroup.MISC, CutsceneEntity::new)
                            .dimensions(EntityDimensions.fixed(0.1f, 0.1f))
                            .trackRangeBlocks(512)
                            .build()
            );


    public static void register() {}
}
