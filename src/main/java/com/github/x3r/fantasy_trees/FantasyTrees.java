package com.github.x3r.fantasy_trees;

import com.github.x3r.fantasy_trees.common.biome.region.*;
import com.github.x3r.fantasy_trees.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import terrablender.api.Regions;

import java.util.List;
import java.util.Objects;

@Mod("fantasy_trees")
public class FantasyTrees {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "fantasy_trees";

    public FantasyTrees() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(this::setup);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockRegistry.registerFantasyBlocks();
        BlockItemRegistry.BLOCK_ITEMS.register(modEventBus);
        BlockItemRegistry.registerFantasyBlockItems();
        ItemRegistry.ITEMS.register(modEventBus);
        ItemRegistry.registerFantasyItems();
       // BlockRegistry.BLOCKS.getEntries().forEach(blockRegistryObject -> ItemRegistry.FantasyTreesItemTab.addToMainTab(blockRegistryObject.get().asItem()));
       //ItemRegistry.FantasyTreesItemTab.addToMainTab(ItemRegistry.GLOWING_COAL.get());
        ItemRegistry.FantasyTreesItemTab.CREATIVE_TABS.register(modEventBus);
        StructureRegistry.STRUCTURE_FEATURES.register(modEventBus);
        FeatureRegistry.FEATURES.register(modEventBus);
        BiomeRegistry.BIOMES.register(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FantasyTreesConfig.SPEC, "fantasy_trees-common.toml");

    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new FantasyOakRegion(new ResourceLocation(MOD_ID, "oak_region"), FantasyTreesConfig.oak_region_weight.get()));
            Regions.register(new FantasySpruceRegion(new ResourceLocation(MOD_ID, "spruce_region"), FantasyTreesConfig.spruce_region_weight.get()));
            Regions.register(new FantasyBirchRegion(new ResourceLocation(MOD_ID, "birch_region"), FantasyTreesConfig.birch_region_weight.get()));
            Regions.register(new FantasyDarkOakRegion(new ResourceLocation(MOD_ID, "dark_oak_region"), FantasyTreesConfig.dark_oak_region_weight.get()));
            Regions.register(new FantasyJungleRegion(new ResourceLocation(MOD_ID, "jungle_region"), FantasyTreesConfig.jungle_region_weight.get()));
            Regions.register(new FantasySavanna(new ResourceLocation(MOD_ID, "savanna_region"), FantasyTreesConfig.savanna_region_weight.get()));
        });
    }
}
