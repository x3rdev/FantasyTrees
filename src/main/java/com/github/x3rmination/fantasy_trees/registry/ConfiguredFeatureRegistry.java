package com.github.x3rmination.fantasy_trees.registry;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.common.features.configuration.FSpruceMediumConfiguration;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.List;

public class ConfiguredFeatureRegistry {

    public static final List<ResourceLocation> FANTASY_SPRUCE_MEDIUM_LIST = List.of(
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_1"),
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_2"),
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_3"),
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_4"),
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_5"),
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_6"),
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_7"),
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_8"),
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_9"),
            new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_10")
    );

    public static final Holder<ConfiguredFeature<FSpruceMediumConfiguration, ?>> FANTASY_SPRUCE_MEDIUM = FeatureUtils.register(
            "fantasy_spruce_medium",
            FeatureRegistry.FANTASY_SPRUCE_MEDIUM.get(),
            new FSpruceMediumConfiguration(FANTASY_SPRUCE_MEDIUM_LIST));

}
