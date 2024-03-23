package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeatureRegistry {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, FantasyTrees.MOD_ID);
    public static final RegistryObject<Feature<RandomPatchConfiguration>> FANTASY_FOREST_GRASS = FEATURES.register("fantasy_forest_grass",
            () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));
    public static final RegistryObject<Feature<RandomPatchConfiguration>> FANTASY_VANILLA_FLOWERS = FEATURES.register("fantasy_vanilla_flowers",
            () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));
    public static final RegistryObject<Feature<RandomPatchConfiguration>> FANTASY_FLOWERS = FEATURES.register("fantasy_flowers",
            () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));
}
