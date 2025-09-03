package dev.titanite.sparkwave.exposureexpanded;

import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.latvian.mods.kubejs.integration.architectury.ArchitecturyIntegration;
import io.github.mortuusars.exposure.Exposure;
import io.github.mortuusars.exposure.neoforge.event.NeoForgeClientEvents;
import io.github.mortuusars.exposure.world.camera.ExposureType;
import io.github.mortuusars.exposure.world.camera.film.properties.ColorBalance;
import io.github.mortuusars.exposure.world.camera.film.properties.FilmStyle;
import io.github.mortuusars.exposure.world.camera.film.properties.HSB;
import io.github.mortuusars.exposure.world.camera.film.properties.Levels;
import io.github.mortuusars.exposure.world.item.FilmRollItem;
import io.github.mortuusars.exposure_polaroid.world.item.InstantSlideItem;
import net.fabricmc.loader.impl.discovery.ModDiscoverer;
import net.fabricmc.loader.impl.discovery.ModLoadCondition;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ModCheck;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;
import org.apache.logging.log4j.core.util.Loader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ExposureExpanded {
    public static final String MOD_ID = "exposure_expanded";

    public static final Logger LOGGER = LoggerFactory.getLogger("Exposure: Expanded");

    public static boolean is_polaroid_loaded = true;

    public static void init() {
        try {
            if (Platform.getMod("exposure_polaroid") != null){
                is_polaroid_loaded = false;
                POLAROID_ITEMS.register();
                LOGGER.info("Exposure Polaroid hook successful.");
            }
        }
        catch (Exception AssertionError){
            LOGGER.info("Exposure Polaroid not present. Continuing.");
        }
        ITEMS.register();
    }
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);
    public static final DeferredRegister<Item> POLAROID_ITEMS = DeferredRegister.create(MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> TELESCOPIC_LENS = ITEMS.register("telescopic_lens", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.telescopic_lens.tooltip"));
    public static final RegistrySupplier<Item> PANORAMIC_LENS = ITEMS.register("panoramic_lens", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.panoramic_lens.tooltip"));

    public static final RegistrySupplier<Item> GAMEBOY_FILM = ITEMS.register("gameboy_film", () ->
            new FilmRollItem(ExposureType.COLOR, 8962160, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))).stacksTo(16)
                    .component(Exposure.DataComponents.FILM_COLOR_PALETTE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "gameboy"))));
    public static final RegistrySupplier<Item> NES_FILM = ITEMS.register("nes_film", () ->
            new FilmRollItem(ExposureType.COLOR, 65280, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))).stacksTo(16)
                    .component(Exposure.DataComponents.FILM_COLOR_PALETTE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "nes"))));
    public static final RegistrySupplier<Item> C64_FILM = ITEMS.register("c64_film", () ->
            new FilmRollItem(ExposureType.COLOR, 10508195, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))).stacksTo(16)
                    .component(Exposure.DataComponents.FILM_COLOR_PALETTE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "c64"))));
    public static final RegistrySupplier<Item> CGA_FILM = ITEMS.register("cga_film", () ->
            new FilmRollItem(ExposureType.COLOR, 16711935, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))).stacksTo(16)
                    .component(Exposure.DataComponents.FILM_COLOR_PALETTE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "cga"))));

    public static final RegistrySupplier<Item> INSTANT_GAMEBOY_SLIDE = POLAROID_ITEMS.register("instant_gameboy_slide", () -> is_polaroid_loaded?
            PolaroidHandler.create_item(ExposureType.COLOR, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure")))
                    .component(Exposure.DataComponents.FILM_COLOR_PALETTE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "gameboy")))
            : new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> INSTANT_NES_SLIDE = POLAROID_ITEMS.register("instant_nes_slide", () -> is_polaroid_loaded?
            PolaroidHandler.create_item(ExposureType.COLOR, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure")))
                    .component(Exposure.DataComponents.FILM_COLOR_PALETTE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "nes")))
            : new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> INSTANT_C64_SLIDE = POLAROID_ITEMS.register("instant_c64_slide", () -> is_polaroid_loaded?
            PolaroidHandler.create_item(ExposureType.COLOR, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure")))
                    .component(Exposure.DataComponents.FILM_COLOR_PALETTE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "c64")))
            : new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> INSTANT_CGA_SLIDE = POLAROID_ITEMS.register("instant_cga_slide", () -> is_polaroid_loaded?
            PolaroidHandler.create_item(ExposureType.COLOR, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure")))
                    .component(Exposure.DataComponents.FILM_COLOR_PALETTE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "cga")))
            : new Item(new Item.Properties()));

    public static final RegistrySupplier<Item> HICAP_BLACK_AND_WHITE_FILM = ITEMS.register("hicap_black_and_white_film", () ->
            new FilmRollItem(ExposureType.BLACK_AND_WHITE, FilmRollItem.BAR_BLACK_AND_WHITE, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))).stacksTo(16)
                    .component(Exposure.DataComponents.FILM_FRAME_COUNT, 32)));
    public static final RegistrySupplier<Item> HICAP_COLOR_FILM = ITEMS.register("hicap_color_film", () ->
            new FilmRollItem(ExposureType.COLOR, FilmRollItem.BAR_COLOR, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))).stacksTo(16)
                    .component(Exposure.DataComponents.FILM_FRAME_COUNT, 32)));

    public static final RegistrySupplier<Item> HIRES_BLACK_AND_WHITE_FILM = ITEMS.register("hires_black_and_white_film", () ->
            new FilmRollItem(ExposureType.BLACK_AND_WHITE, FilmRollItem.BAR_BLACK_AND_WHITE, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))).stacksTo(16)
                    .component(Exposure.DataComponents.FILM_FRAME_SIZE, 640)));
    public static final RegistrySupplier<Item> HIRES_COLOR_FILM = ITEMS.register("hires_color_film", () ->
            new FilmRollItem(ExposureType.COLOR, FilmRollItem.BAR_COLOR, new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))).stacksTo(16)
                    .component(Exposure.DataComponents.FILM_FRAME_SIZE, 640)));
    public static final RegistrySupplier<Item> INSTANT_HIRES_BLACK_AND_WHITE_SLIDE = POLAROID_ITEMS.register("instant_hires_black_and_white_slide", () -> is_polaroid_loaded?
            PolaroidHandler.create_item(ExposureType.BLACK_AND_WHITE, (new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))))
                    .component(io.github.mortuusars.exposure.Exposure.DataComponents.FILM_STYLE, FilmStyle.create()
                            .withContrast(0.2F)
                            .withLevels(new Levels(0, 135, 255, 25, 255))
                            .withHSB(new HSB(0.0F, 0.05F, 0.05F))
                            .withColorBalance(new ColorBalance(0.03F, 0.01F, -0.01F)))
                    .component(Exposure.DataComponents.FILM_FRAME_SIZE, 480))
            : new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> INSTANT_HIRES_COLOR_SLIDE = POLAROID_ITEMS.register("instant_hires_color_slide", () -> is_polaroid_loaded?
            PolaroidHandler.create_item(ExposureType.COLOR, (new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))))
                    .component(io.github.mortuusars.exposure.Exposure.DataComponents.FILM_STYLE, FilmStyle.create()
                            .withContrast(0.2F)
                            .withLevels(new Levels(0, 135, 255, 25, 255))
                            .withHSB(new HSB(0.0F, 0.05F, 0.05F))
                            .withColorBalance(new ColorBalance(0.03F, 0.01F, -0.01F)))
                    .component(Exposure.DataComponents.FILM_FRAME_SIZE, 480))
            : new Item(new Item.Properties()));

    public static final RegistrySupplier<Item> ANTIALIAS_FILTER = ITEMS.register("antialias_filter", () ->
            new ItemWith2ShiftTooltips(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))),
                    "item.exposure_expanded.antialias_filter.tooltip1",
                    "item.exposure_expanded.antialias_filter.tooltip2"));
    public static final RegistrySupplier<Item> ART_FILTER = ITEMS.register("art_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.art_filter.tooltip"));
    public static final RegistrySupplier<Item> BITS_FILTER = ITEMS.register("bits_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.bits_filter.tooltip"));
    public static final RegistrySupplier<Item> BLOBS_FILTER = ITEMS.register("blobs_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.blobs_filter.tooltip"));
    // Too similar to art
    //public static final RegistrySupplier<Item> BLOBS_2_FILTER = ITEMS.register("blobs_2_filter", () ->
    //        new ItemWithShiftTooltip(new Item.Properties(), "item.exposure_expanded.blobs_2_filter.tooltip"));
    public static final RegistrySupplier<Item> BLUR_FILTER = ITEMS.register("blur_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.blur_filter.tooltip"));
    public static final RegistrySupplier<Item> BUMPY_FILTER = ITEMS.register("bumpy_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.bumpy_filter.tooltip"));
    public static final RegistrySupplier<Item> COLOR_CONVOLVE_FILTER = ITEMS.register("color_convolve_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.color_convolve_filter.tooltip"));
    // Too similar to using green filter
    //public static final RegistrySupplier<Item> CREEPER_FILTER = ITEMS.register("creeper_filter", () ->
    //        new ItemWithShiftTooltip(new Item.Properties(), "item.exposure_expanded.creeper_filter.tooltip"));
    public static final RegistrySupplier<Item> DECONVERGE_FILTER = ITEMS.register("deconverge_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.deconverge_filter.tooltip"));
    public static final RegistrySupplier<Item> DESATURATE_FILTER = ITEMS.register("desaturate_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.desaturate_filter.tooltip"));
    public static final RegistrySupplier<Item> FLIP_FILTER = ITEMS.register("flip_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.flip_filter.tooltip"));
    // Unnoticeable
    //public static final RegistrySupplier<Item> FXAA_FILTER = ITEMS.register("fxaa_filter", () ->
    //        new ItemWithShiftTooltip(new Item.Properties(), "item.exposure_expanded.fxaa_filter.tooltip"));
    // Same thing as with creeper
    //public static final RegistrySupplier<Item> GREEN_FILTER = ITEMS.register("green_filter", () ->
    //        new ItemWithShiftTooltip(new Item.Properties(), "item.exposure_expanded.green_filter.tooltip"));
    // Very unnoticeable dithering
    //public static final RegistrySupplier<Item> NOTCH_FILTER = ITEMS.register("notch_filter", () ->
    //        new ItemWithShiftTooltip(new Item.Properties(), "item.exposure_expanded.notch_filter.tooltip"));
    public static final RegistrySupplier<Item> NTSC_FILTER = ITEMS.register("ntsc_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.ntsc_filter.tooltip"));
    // I don't even know what this one is supposed to be
    //public static final RegistrySupplier<Item> OUTLINE_FILTER = ITEMS.register("outline_filter", () ->
    //        new ItemWithShiftTooltip(new Item.Properties(), "item.exposure_expanded.outline_filter.tooltip"));
    public static final RegistrySupplier<Item> PENCIL_FILTER = ITEMS.register("pencil_filter", () ->
            new ItemWith2ShiftTooltips(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))),
                    "item.exposure_expanded.pencil_filter.tooltip1",
                    "item.exposure_expanded.pencil_filter.tooltip2"));
    // Motion blur is invisible on photos
    //public static final RegistrySupplier<Item> PHOSPHOR_FILTER = ITEMS.register("phosphor_filter", () ->
    //        new ItemWithShiftTooltip(new Item.Properties(), "item.exposure_expanded.phosphor_filter.tooltip"));
    public static final RegistrySupplier<Item> SCAN_PINCUSHION_FILTER = ITEMS.register("scan_pincushion_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.scan_pincushion_filter.tooltip"));
    public static final RegistrySupplier<Item> SOBEL_FILTER = ITEMS.register("sobel_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.sobel_filter.tooltip"));
    public static final RegistrySupplier<Item> SPIDER_FILTER = ITEMS.register("spider_filter", () ->
            new ItemWithShiftTooltip(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))), "item.exposure_expanded.spider_filter.tooltip"));
    public static final RegistrySupplier<Item> WOBBLE_FILTER = ITEMS.register("wobble_filter", () ->
            new ItemWith2ShiftTooltips(new Item.Properties().arch$tab(ResourceKey.create(Registries.CREATIVE_MODE_TAB, Exposure.resource("exposure"))),
                    "item.exposure_expanded.wobble_filter.tooltip1",
                    "item.exposure_expanded.wobble_filter.tooltip2"));

    public static class LootTables {
        public static final ResourceKey<LootTable> ABANDONED_MINESHAFT_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/abandoned_mineshaft"));
        public static final ResourceKey<LootTable> ANCIENT_CITY_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/ancient_city"));
        public static final ResourceKey<LootTable> BASTION_OTHER_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/bastion_other"));
        public static final ResourceKey<LootTable> BURIED_TREASURE_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/buried_treasure"));
        public static final ResourceKey<LootTable> DESERT_PYRAMID_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/desert_pyramid"));
        public static final ResourceKey<LootTable> END_CITY_TREASURE_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/end_city_treasure"));
        public static final ResourceKey<LootTable> IGLOO_CHEST_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/igloo_chest"));
        public static final ResourceKey<LootTable> JUNGLE_TEMPLE_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/jungle_temple"));
        public static final ResourceKey<LootTable> NETHER_BRIDGE_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/nether_bridge"));
        public static final ResourceKey<LootTable> PILLAGER_OUTPOST_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/pillager_outpost"));
        public static final ResourceKey<LootTable> RUINED_PORTAL_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/ruined_portal"));
        public static final ResourceKey<LootTable> SHIPWRECK_SUPPLY_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/shipwreck_supply"));
        public static final ResourceKey<LootTable> SIMPLE_DUNGEON_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/simple_dungeon"));
        public static final ResourceKey<LootTable> TRIAL_CHAMBER_CORRIDOR_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/trial_chamber_corridor"));
        public static final ResourceKey<LootTable> UNDERWATER_RUIN_SMALL_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/underwater_ruin_small"));
        public static final ResourceKey<LootTable> VILLAGE_DESERT_HOUSE_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/village_desert_house"));
        public static final ResourceKey<LootTable> VILLAGE_PLAINS_HOUSE_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/village_plains_house"));
        public static final ResourceKey<LootTable> VILLAGE_SAVANNA_HOUSE_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/village_savanna_house"));
        public static final ResourceKey<LootTable> VILLAGE_SNOWY_HOUSE_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/village_snowy_house"));
        public static final ResourceKey<LootTable> VILLAGE_TEMPLE_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/village_temple"));
        public static final ResourceKey<LootTable> WOODLAND_MANSION_INJECT =
                ResourceKey.create(net.minecraft.core.registries.Registries.LOOT_TABLE, ExposureExpanded.resource("chests/abandoned_mineshaft"));
    }

    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath("exposure_expanded", path);
    }
}