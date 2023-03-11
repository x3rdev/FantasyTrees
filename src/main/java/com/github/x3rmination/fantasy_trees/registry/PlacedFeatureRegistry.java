package com.github.x3rmination.fantasy_trees.registry;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.placement.*;

public class PlacedFeatureRegistry {

    public static final Holder<PlacedFeature> FANTASY_SPRUCE_MEDIUM_PLACED = PlacementUtils.register("fantasy_spruce_medium_placed",
            ConfiguredFeatureRegistry.FANTASY_SPRUCE_MEDIUM, RarityFilter.onAverageOnceEvery(1),
            CountPlacement.of(1), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
}
