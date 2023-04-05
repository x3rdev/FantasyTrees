package com.github.x3rmination.fantasy_trees.registry;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.*;

public class PlacedFeatureRegistry {

    public static final Holder<PlacedFeature> FANTASY_SPRUCE_MEDIUM_PLACED = PlacementUtils.register("fantasy_spruce_medium_placed",
            ConfiguredFeatureRegistry.FANTASY_SPRUCE_MEDIUM, RarityFilter.onAverageOnceEvery(1),
            CountPlacement.of(1), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread());

    public static final Holder<PlacedFeature> FANTASY_OAK_SMALL_PLACED = PlacementUtils.register("fantasy_oak_small_placed",
            ConfiguredFeatureRegistry.FANTASY_OAK_SMALL, VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));

    public static final Holder<PlacedFeature> FANTASY_OAK_MEDIUM_PLACED = PlacementUtils.register("fantasy_oak_medium_placed",
            ConfiguredFeatureRegistry.FANTASY_OAK_MEDIUM, RarityFilter.onAverageOnceEvery(3),
            CountPlacement.of(1), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), InSquarePlacement.spread());
}
