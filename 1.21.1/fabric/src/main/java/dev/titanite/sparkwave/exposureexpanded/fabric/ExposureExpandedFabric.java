package dev.titanite.sparkwave.exposureexpanded.fabric;

import io.github.mortuusars.exposure.Config;
import net.fabricmc.api.ModInitializer;

import dev.titanite.sparkwave.exposureexpanded.ExposureExpanded;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public final class ExposureExpandedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        ExposureExpanded.init();

        LootTableEvents.MODIFY.register(ExposureExpandedFabric::modifyLoot);
    }

    private static void modifyLoot(ResourceKey<LootTable> tableKey, LootTable.Builder builder,
                                   LootTableSource source, HolderLookup.Provider provider) {
        if (!Config.Common.GENERATE_LOOT.get() || !source.isBuiltin())
            return;

        if (BuiltInLootTables.ABANDONED_MINESHAFT.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.ABANDONED_MINESHAFT_INJECT))
                    .build());
        }
        if (BuiltInLootTables.ANCIENT_CITY.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.ANCIENT_CITY_INJECT))
                    .build());
        }
        if (BuiltInLootTables.BASTION_OTHER.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.BASTION_OTHER_INJECT))
                    .build());
        }
        if (BuiltInLootTables.BURIED_TREASURE.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.BURIED_TREASURE_INJECT))
                    .build());
        }
        if (BuiltInLootTables.DESERT_PYRAMID.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.DESERT_PYRAMID_INJECT))
                    .build());
        }
        if (BuiltInLootTables.END_CITY_TREASURE.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.END_CITY_TREASURE_INJECT))
                    .build());
        }
        if (BuiltInLootTables.IGLOO_CHEST.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.IGLOO_CHEST_INJECT))
                    .build());
        }
        if (BuiltInLootTables.JUNGLE_TEMPLE.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.JUNGLE_TEMPLE_INJECT))
                    .build());
        }
        if (BuiltInLootTables.NETHER_BRIDGE.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.NETHER_BRIDGE_INJECT))
                    .build());
        }
        if (BuiltInLootTables.PILLAGER_OUTPOST.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.PILLAGER_OUTPOST_INJECT))
                    .build());
        }
        if (BuiltInLootTables.RUINED_PORTAL.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.RUINED_PORTAL_INJECT))
                    .build());
        }
        if (BuiltInLootTables.SHIPWRECK_SUPPLY.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.SHIPWRECK_SUPPLY_INJECT))
                    .build());
        }
        if (BuiltInLootTables.SIMPLE_DUNGEON.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.SIMPLE_DUNGEON_INJECT))
                    .build());
        }
        if (BuiltInLootTables.TRIAL_CHAMBERS_CORRIDOR.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.TRIAL_CHAMBER_CORRIDOR_INJECT))
                    .build());
        }
        if (BuiltInLootTables.UNDERWATER_RUIN_SMALL.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.UNDERWATER_RUIN_SMALL_INJECT))
                    .build());
        }
        if (BuiltInLootTables.VILLAGE_DESERT_HOUSE.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.VILLAGE_DESERT_HOUSE_INJECT))
                    .build());
        }
        if (BuiltInLootTables.VILLAGE_PLAINS_HOUSE.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.VILLAGE_PLAINS_HOUSE_INJECT))
                    .build());
        }
        if (BuiltInLootTables.VILLAGE_SAVANNA_HOUSE.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.VILLAGE_SAVANNA_HOUSE_INJECT))
                    .build());
        }
        if (BuiltInLootTables.VILLAGE_SNOWY_HOUSE.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.VILLAGE_SNOWY_HOUSE_INJECT))
                    .build());
        }
        if (BuiltInLootTables.VILLAGE_TEMPLE.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.VILLAGE_TEMPLE_INJECT))
                    .build());
        }
        if (BuiltInLootTables.WOODLAND_MANSION.equals(tableKey)) {
            builder.pool(LootPool.lootPool()
                    .add(NestedLootTable.lootTableReference(ExposureExpanded.LootTables.WOODLAND_MANSION_INJECT))
                    .build());
        }
    }
}
