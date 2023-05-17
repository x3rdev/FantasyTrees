package com.github.x3rmination.fantasy_trees;

import com.github.x3rmination.fantasy_trees.common.biome.region.FantasyOakRegion;
import com.github.x3rmination.fantasy_trees.common.biome.region.FantasySpruceRegion;
import com.github.x3rmination.fantasy_trees.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
        forgeEventBus.addListener(this::generateTrees);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockRegistry.registerFantasyBlocks();
        BlockItemRegistry.BLOCK_ITEMS.register(modEventBus);
        BlockItemRegistry.registerFantasyBlockItems();
        ItemRegistry.ITEMS.register(modEventBus);
        ItemRegistry.registerFantasyItems();
        StructureRegistry.STRUCTURE_FEATURES.register(modEventBus);
        FeatureRegistry.FEATURES.register(modEventBus);
        BiomeRegistry.BIOMES.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new FantasyOakRegion(new ResourceLocation(MOD_ID, "oak_region"), 5));
            Regions.register(new FantasySpruceRegion(new ResourceLocation(MOD_ID, "spruce_region"), 5));
        });
    }

    private void generateTrees(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> vegetal_decoration = event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);

        if(Objects.equals(event.getName(), BiomeRegistry.FANTASY_TAIGA_BIOME.get().getRegistryName())) {
            vegetal_decoration.add(PlacedFeatureRegistry.FANTASY_SPRUCE_MEDIUM_PLACED);
        }
        if(Objects.equals(event.getName(), BiomeRegistry.FANTASY_FOREST_BIOME.get().getRegistryName())) {
            vegetal_decoration.add(PlacedFeatureRegistry.FANTASY_OAK_SMALL_PLACED);
            vegetal_decoration.add(PlacedFeatureRegistry.FANTASY_OAK_MEDIUM_PLACED);
            vegetal_decoration.add(PlacedFeatureRegistry.FANTASY_FOREST_VANILLA_TREES);
            vegetal_decoration.add(PlacedFeatureRegistry.FANTASY_FOREST_GRASS_PLACED);
            vegetal_decoration.add(PlacedFeatureRegistry.FANTASY_FLOWERS_PLACED);
        }
    }
}
