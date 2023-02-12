package com.github.x3rmination.fantasy_trees.registry;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.common.features.configuration.FSpruceMediumConfiguration;
import com.github.x3rmination.fantasy_trees.common.features.features.FSpruceMediumFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeatureRegistry {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, FantasyTrees.MOD_ID);

    public static final RegistryObject<Feature<FSpruceMediumConfiguration>> FANTASY_SPRUCE_MEDIUM = FEATURES.register("fantasy_spruce_medium",
            () -> new FSpruceMediumFeature(FSpruceMediumConfiguration.CODEC));

}
