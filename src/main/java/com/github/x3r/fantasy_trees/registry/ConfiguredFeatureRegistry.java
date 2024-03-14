package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class ConfiguredFeatureRegistry {
    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(FantasyTrees.MOD_ID, name));
    }
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_FOREST_GRASS  = createKey("fantasy_forest_grass");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_VANILLA_FLOWERS  = createKey("fantasy_vanilla_flowers");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_FLOWERS  = createKey("fantasy_flowers");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        register(context,
                FANTASY_FOREST_GRASS,
                FeatureRegistry.FANTASY_FOREST_GRASS.get(),
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.TALL_GRASS.defaultBlockState(), 1).add(Blocks.FERN.defaultBlockState(), 1).build()))));
        register(context,
                FANTASY_VANILLA_FLOWERS,
                FeatureRegistry.FANTASY_VANILLA_FLOWERS.get(),
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, flowerBlockConfiguration()));
        register(context,
                FANTASY_FLOWERS,
                FeatureRegistry.FANTASY_FLOWERS.get(),
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockRegistry.FANTASY_FLOWER.get()))));
    }
    
    private static SimpleBlockConfiguration flowerBlockConfiguration() {
        SimpleWeightedRandomList.Builder<BlockState> list = SimpleWeightedRandomList.builder();
        list.add(Blocks.DANDELION.defaultBlockState(), 1);
        list.add(Blocks.POPPY.defaultBlockState(), 1);
        list.add(Blocks.BLUE_ORCHID.defaultBlockState(), 1);
        list.add(Blocks.ALLIUM.defaultBlockState(), 1);
        list.add(Blocks.AZURE_BLUET.defaultBlockState(), 1);
        list.add(Blocks.RED_TULIP.defaultBlockState(), 1);
        list.add(Blocks.ORANGE_TULIP.defaultBlockState(), 1);
        list.add(Blocks.WHITE_TULIP.defaultBlockState(), 1);
        list.add(Blocks.PINK_TULIP.defaultBlockState(), 1);
        list.add(Blocks.OXEYE_DAISY.defaultBlockState(), 1);
        list.add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1);
        list.add(Blocks.RED_MUSHROOM.defaultBlockState(), 1);
        list.add(Blocks.SUNFLOWER.defaultBlockState(), 1);
        list.add(Blocks.LILAC.defaultBlockState(), 1);
        list.add(Blocks.ROSE_BUSH.defaultBlockState(), 1);
        list.add(Blocks.PEONY.defaultBlockState(), 1);
        list.add(Blocks.CORNFLOWER.defaultBlockState(), 1);
        list.add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 1);
        list.build();
        return new SimpleBlockConfiguration(new WeightedStateProvider(list));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
