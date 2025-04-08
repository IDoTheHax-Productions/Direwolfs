package net.idothehax.direwolfs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.idothehax.direwolfs.entity.DirewolfEntity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Direwolfs implements ModInitializer {
    public static String MOD_ID = "direwolfs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final EntityType<DirewolfEntity> DIREWOLF = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MOD_ID, "direwolf"),
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(DirewolfEntity::new)
                    .defaultAttributes(DirewolfEntity::createDirewolfAttributes)
                    .dimensions(EntityDimensions.fixed(1.3F, 1.8F))
                    .build()
    );

    public static final Item DIREWOLF_SPAWN_EGG = new SpawnEggItem(
            DIREWOLF,
            0x000000, // Primary color
            0xFFFFFF, // Secondary color
            new Item.Settings()
    );

    public static void registerItems() {
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "direwolf_spawn_egg"), DIREWOLF_SPAWN_EGG);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("AWOOOOOOOOOOOOOO From Direwolfs");
        registerItems();
    }
}
