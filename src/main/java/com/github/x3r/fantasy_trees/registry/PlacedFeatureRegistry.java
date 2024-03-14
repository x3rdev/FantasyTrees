package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PlacedFeatureRegistry {

    public static final ResourceKey<PlacedFeature> FANTASY_FOREST_GRASS_CHECKED  = createKey("fantasy_forest_grass");
    public static final ResourceKey<PlacedFeature> FANTASY_VANILLA_FLOWERS_CHECKED  = createKey("fantasy_vanilla_flowers");
    public static final ResourceKey<PlacedFeature> FANTASY_FLOWERS_CHECKED  = createKey("fantasy_flowers");

    public static ResourceKey<PlacedFeature> createKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(FantasyTrees.MOD_ID, name));
    }
    
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_FOREST_GRASS = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_FOREST_GRASS);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_VANILLA_FLOWERS = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_VANILLA_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_FLOWERS = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_VANILLA_FLOWERS);

        register(context, FANTASY_FOREST_GRASS_CHECKED,
                FANTASY_FOREST_GRASS, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(5), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_VANILLA_FLOWERS_CHECKED,
                FANTASY_VANILLA_FLOWERS, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(3), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_FLOWERS_CHECKED,
                FANTASY_FLOWERS, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(3), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
    }
    
    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers)
    {
        register(context, placedFeatureKey, configuredFeature, List.of(modifiers));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers)
    {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeature, modifiers));
    }
}
