package com.github.x3rmination.fantasy_trees.registry;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import com.github.x3rmination.fantasy_trees.common.features.features.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeatureRegistry {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, FantasyTrees.MOD_ID);

    public static final RegistryObject<Feature<TreeConfiguration>> FANTASY_SPRUCE_MEDIUM = FEATURES.register("fantasy_spruce_medium",
            () -> new FSpruceMediumFeature(TreeConfiguration.CODEC));
    public static final RegistryObject<Feature<TreeConfiguration>> FANTASY_OAK_SMALL = FEATURES.register("fantasy_oak_small",
            () -> new FOakSmallFeature(TreeConfiguration.CODEC));
    public static final RegistryObject<Feature<TreeConfiguration>> FANTASY_OAK_MEDIUM = FEATURES.register("fantasy_oak_medium",
            () -> new FOakMediumFeature(TreeConfiguration.CODEC));

    public static final RegistryObject<Feature<TreeConfiguration>> FANTASY_BIRCH_SMALL = FEATURES.register("fantasy_birch_small",
            () -> new FBirchSmallFeature(TreeConfiguration.CODEC));

    public static final RegistryObject<Feature<TreeConfiguration>> FANTASY_BIRCH_MEDIUM = FEATURES.register("fantasy_birch_medium",
            () -> new FBirchMediumFeature(TreeConfiguration.CODEC));

    public static final RegistryObject<Feature<RandomPatchConfiguration>> FANTASY_FOREST_GRASS = FEATURES.register("fantasy_forest_grass",
            () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));

    public static final RegistryObject<Feature<RandomPatchConfiguration>> FANTASY_VANILLA_FLOWERS = FEATURES.register("fantasy_vanilla_flowers",
            () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));

    public static final RegistryObject<Feature<RandomPatchConfiguration>> FANTASY_FLOWERS = FEATURES.register("fantasy_flowers",
            () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));
}
