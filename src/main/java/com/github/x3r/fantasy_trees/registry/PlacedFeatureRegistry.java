package com.github.x3r.fantasy_trees.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;

public class PlacedFeatureRegistry {

    public static final Holder<PlacedFeature> FANTASY_ACACIA_SMALL_PLACED = PlacementUtils.register("fantasy_acacia_small_placed",
            ConfiguredFeatureRegistry.FANTASY_ACACIA_SMALL, RarityFilter.onAverageOnceEvery(1),
            CountPlacement.of(4), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(),
            BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
    public static final Holder<PlacedFeature> FANTASY_BIRCH_SMALL_PLACED = PlacementUtils.register("fantasy_birch_small_placed",
            ConfiguredFeatureRegistry.FANTASY_BIRCH_SMALL, RarityFilter.onAverageOnceEvery(1),
            CountPlacement.of(4), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(),
            BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));

    public static final Holder<PlacedFeature> FANTASY_BIRCH_MEDIUM_PLACED = PlacementUtils.register("fantasy_birch_medium_placed",
            ConfiguredFeatureRegistry.FANTASY_BIRCH_MEDIUM, RarityFilter.onAverageOnceEvery(2),
            CountPlacement.of(1), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(),
            BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));

        register(context, FANTASY_ACACIA_SMALL_CHECKED,
                FANTASY_ACACIA_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(4), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        register(context, FANTASY_BIRCH_SMALL_CHECKED,
                FANTASY_BIRCH_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(4), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        register(context, FANTASY_BIRCH_MEDIUM_CHECKED,
                FANTASY_BIRCH_MEDIUM, RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        register(context, FANTASY_OAK_SMALL_CHECKED,
                FANTASY_OAK_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(4), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), InSquarePlacement.spread(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        register(context, FANTASY_OAK_MEDIUM_CHECKED,
                FANTASY_OAK_MEDIUM, RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        register(context, FANTASY_JUNGLE_SMALL_CHECKED,
                FANTASY_JUNGLE_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        register(context, FANTASY_JUNGLE_MEDIUM_CHECKED,
                FANTASY_JUNGLE_MEDIUM, RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        register(context, FANTASY_SPRUCE_SMALL_CHECKED,
                FANTASY_SPRUCE_SMALL, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        register(context, FANTASY_SPRUCE_MEDIUM_CHECKED,
                FANTASY_SPRUCE_MEDIUM, RarityFilter.onAverageOnceEvery(2),
                CountPlacement.of(1), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),
                BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        register(context, FANTASY_FOREST_GRASS_CHECKED,
                FANTASY_FOREST_GRASS, RarityFilter.onAverageOnceEvery(1),
                CountPlacement.of(5), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, InSquarePlacement.spread());
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

    public static final Holder<PlacedFeature> FANTASY_OAK_MEDIUM_PLACED = PlacementUtils.register("fantasy_oak_medium_placed",
            ConfiguredFeatureRegistry.FANTASY_OAK_MEDIUM, RarityFilter.onAverageOnceEvery(2),
            CountPlacement.of(1), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(),
            BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));

    public static final Holder<PlacedFeature> FANTASY_SPRUCE_MEDIUM_PLACED = PlacementUtils.register("fantasy_spruce_medium_placed",
            ConfiguredFeatureRegistry.FANTASY_SPRUCE_MEDIUM, RarityFilter.onAverageOnceEvery(2),
            CountPlacement.of(1), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(),
            BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));

    //------

    public static final Holder<PlacedFeature> FANTASY_FOREST_VANILLA_TREES = PlacementUtils.register("fantasy_forest_vanilla_trees",
            TreeFeatures.OAK, RarityFilter.onAverageOnceEvery(1),
            CountPlacement.of(2), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread(),
            BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultBlockState(), BlockPos.ZERO)));

    public static final Holder<PlacedFeature> FANTASY_FOREST_GRASS_PLACED = PlacementUtils.register("fantasy_forest_grass_placed",
            ConfiguredFeatureRegistry.FANTASY_FOREST_GRASS, RarityFilter.onAverageOnceEvery(1),
            CountPlacement.of(5), PlacementUtils.HEIGHTMAP, InSquarePlacement.spread());

    public static final Holder<PlacedFeature> FANTASY_VANILLA_FLOWERS_PLACED = PlacementUtils.register("fantasy_vanilla_flowers_placed",
            ConfiguredFeatureRegistry.FANTASY_VANILLA_FLOWERS, RarityFilter.onAverageOnceEvery(1),
            CountPlacement.of(3), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread());

    public static final Holder<PlacedFeature> FANTASY_FLOWERS_PLACED = PlacementUtils.register("fantasy_flowers_placed",
            ConfiguredFeatureRegistry.FANTASY_FLOWERS, RarityFilter.onAverageOnceEvery(1),
            CountPlacement.of(3), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread());
}
