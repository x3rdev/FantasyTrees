package com.github.x3rmination.fantasy_trees.registry;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import com.github.x3rmination.fantasy_trees.common.features.features.FOakMediumFeature;
import com.github.x3rmination.fantasy_trees.common.features.features.FOakSmallFeature;
import com.github.x3rmination.fantasy_trees.common.features.features.FSpruceMediumFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
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
}
