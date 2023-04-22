package com.github.x3rmination.fantasy_trees.registry;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Map;

public class ConfiguredFeatureRegistry {

    //TODO When nbt files are finalized, fix this to actually be numbered properly
    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_SPRUCE_MEDIUM_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_1"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_2"), new Pair<>(5, -3)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_3"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_4"), new Pair<>(5, 0)),
//            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_5"),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_6"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_7"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_8"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_9"), new Pair<>(5, 0)),

            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_1n"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_2n"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_3n"), new Pair<>(5, 0))
    );

    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_OAK_SMALL_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_1"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_2"), new Pair<>(1, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_3"), new Pair<>(5, -3)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_4"), new Pair<>(1, -5)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_5"), new Pair<>(5, 0)),
//            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_6"), new Pair<>(5, -3)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_7"), new Pair<>(5, -3)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_8"), new Pair<>(5,-2))
    );

    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_OAK_MEDIUM_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_medium_1"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_medium_2"), new Pair<>(5, 0))
    );

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANTASY_SPRUCE_MEDIUM = FeatureUtils.register(
            "fantasy_spruce_medium",
            FeatureRegistry.FANTASY_SPRUCE_MEDIUM.get(),
            new TreeConfiguration(FANTASY_SPRUCE_MEDIUM_LIST));

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANTASY_OAK_SMALL = FeatureUtils.register(
            "fantasy_oak_small",
            FeatureRegistry.FANTASY_OAK_SMALL.get(),
            new TreeConfiguration(FANTASY_OAK_SMALL_LIST));

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FANTASY_OAK_MEDIUM = FeatureUtils.register(
            "fantasy_oak_medium",
            FeatureRegistry.FANTASY_OAK_MEDIUM.get(),
            new TreeConfiguration(FANTASY_OAK_MEDIUM_LIST));

}
