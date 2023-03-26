package com.github.x3rmination.fantasy_trees;

import com.github.x3rmination.fantasy_trees.common.biome.region.FantasyOakRegion;
import com.github.x3rmination.fantasy_trees.common.biome.region.FantasySpruceRegion;
import com.github.x3rmination.fantasy_trees.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
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
            Regions.register(new FantasyOakRegion(new ResourceLocation(MOD_ID, "oak_region"), 3));
            Regions.register(new FantasySpruceRegion(new ResourceLocation(MOD_ID, "spruce_region"), 3));
        });
    }

    private void generateTrees(final BiomeLoadingEvent event) {
        List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
        if(BiomeRegistry.FANTASY_TAIGA_BIOME.get().getRegistryName().equals(event.getName())) {
            base.add(PlacedFeatureRegistry.FANTASY_SPRUCE_MEDIUM_PLACED);
        }
        if(BiomeRegistry.FANTASY_FOREST_BIOME.get().getRegistryName().equals(event.getName())) {
            base.add(PlacedFeatureRegistry.FANTASY_OAK_SMALL_PLACED);
            base.add(PlacedFeatureRegistry.FANTASY_OAK_MEDIUM_PLACED);
        }
    }
}
