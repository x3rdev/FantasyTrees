package com.github.x3rmination.fantasy_trees;

import com.github.x3rmination.fantasy_trees.client.ClientEvents;
import com.github.x3rmination.fantasy_trees.common.biome.region.FantasyTreesRegion;
import com.github.x3rmination.fantasy_trees.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import terrablender.api.Regions;

import java.util.List;
import java.util.Set;

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
            Regions.register(new FantasyTreesRegion(new ResourceLocation(MOD_ID, "overworld"), 3));
        });
    }

    private void generateTrees(final BiomeLoadingEvent event) {
        ResourceKey<Biome> key = ResourceKey.create(Registry.BIOME_REGISTRY, event.getName());
        Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(key);
        if(BiomeRegistry.FANTASY_TAIGA_BIOME.get().getRegistryName().equals(event.getName())) {
            List<Holder<PlacedFeature>> base = event.getGeneration().getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION);
            base.add(PlacedFeatureRegistry.FANTASY_SPRUCE_MEDIUM_PLACED);
        }
    }
}
