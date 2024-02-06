package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.features.TreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class PlacedFeatureRegistry {

    public static final ResourceKey<PlacedFeature> FANTASY_ACACIA_SMALL_CHECKED = createKey("fantasy_acacia_small");
    public static final ResourceKey<PlacedFeature> FANTASY_BIRCH_SMALL_CHECKED  = createKey("fantasy_birch_small");
    public static final ResourceKey<PlacedFeature> FANTASY_BIRCH_MEDIUM_CHECKED  = createKey("fantasy_birch_medium");
    public static final ResourceKey<PlacedFeature> FANTASY_OAK_SMALL_CHECKED  = createKey("fantasy_oak_small");
    public static final ResourceKey<PlacedFeature> FANTASY_OAK_MEDIUM_CHECKED  = createKey("fantasy_oak_medium");
    public static final ResourceKey<PlacedFeature> FANTASY_JUNGLE_SMALL_CHECKED  = createKey("fantasy_jungle_small");
    public static final ResourceKey<PlacedFeature> FANTASY_JUNGLE_MEDIUM_CHECKED  = createKey("fantasy_jungle_medium");
    public static final ResourceKey<PlacedFeature> FANTASY_SPRUCE_SMALL_CHECKED  = createKey("fantasy_spruce_small");
    public static final ResourceKey<PlacedFeature> FANTASY_SPRUCE_MEDIUM_CHECKED  = createKey("fantasy_spruce_medium");
    public static final ResourceKey<PlacedFeature> FANTASY_FOREST_GRASS_CHECKED  = createKey("fantasy_forest_grass");
    public static final ResourceKey<PlacedFeature> FANTASY_VANILLA_FLOWERS_CHECKED  = createKey("fantasy_vanilla_flowers");
    public static final ResourceKey<PlacedFeature> FANTASY_FLOWERS_CHECKED  = createKey("fantasy_flowers");

    public static ResourceKey<PlacedFeature> createKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(FantasyTrees.MOD_ID, name));
    }
    
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_ACACIA_SMALL = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_ACACIA_SMALL);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_BIRCH_SMALL = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_BIRCH_SMALL);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_BIRCH_MEDIUM = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_BIRCH_MEDIUM);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_OAK_SMALL = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_OAK_SMALL);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_OAK_MEDIUM = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_OAK_MEDIUM);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_JUNGLE_SMALL = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_JUNGLE_SMALL);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_JUNGLE_MEDIUM = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_JUNGLE_MEDIUM);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_SPRUCE_SMALL = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_SPRUCE_SMALL);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_SPRUCE_MEDIUM = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_SPRUCE_MEDIUM);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_FOREST_GRASS = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_FOREST_GRASS);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_VANILLA_FLOWERS = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_VANILLA_FLOWERS);
        final Holder<ConfiguredFeature<?, ?>> FANTASY_FLOWERS = configuredFeatureGetter.getOrThrow(ConfiguredFeatureRegistry.FANTASY_VANILLA_FLOWERS);

        register(context, FANTASY_ACACIA_SMALL_CHECKED,
                FANTASY_ACACIA_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(4), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_BIRCH_SMALL_CHECKED,
                FANTASY_BIRCH_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(4), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_BIRCH_MEDIUM_CHECKED,
                FANTASY_BIRCH_MEDIUM, RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_OAK_SMALL_CHECKED,
                FANTASY_OAK_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(4), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_OAK_MEDIUM_CHECKED,
                FANTASY_OAK_MEDIUM, RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_JUNGLE_SMALL_CHECKED,
                FANTASY_JUNGLE_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_JUNGLE_MEDIUM_CHECKED,
                FANTASY_JUNGLE_MEDIUM, RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_SPRUCE_SMALL_CHECKED,
                FANTASY_SPRUCE_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
        register(context, FANTASY_SPRUCE_MEDIUM_CHECKED,
                FANTASY_SPRUCE_MEDIUM, RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread());
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
