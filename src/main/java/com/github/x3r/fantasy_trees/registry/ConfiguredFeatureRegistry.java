package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.features.TreeConfiguration;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
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
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class ConfiguredFeatureRegistry {

    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_ACACIA_SMALL_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_acacia_small_1"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_acacia_small_2"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_acacia_small_3"), new Pair<>(5, 0))
    );

    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_BIRCH_SMALL_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_1"), new Pair<>(15, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_2"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_3"), new Pair<>(15, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_4"), new Pair<>(5, -3)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_5"), new Pair<>(15, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_6"), new Pair<>(2, -3)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_7"), new Pair<>(15, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_8"), new Pair<>(15, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_9"), new Pair<>(15, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_small_10"), new Pair<>(15, 0))

    );

    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_BIRCH_MEDIUM_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_medium_1"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_medium_2"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_medium_3"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_medium_4"), new Pair<>(5, -4))
    );

    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_OAK_SMALL_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_1"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_2"), new Pair<>(1, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_3"), new Pair<>(5, -3)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_4"), new Pair<>(1, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_5"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_6"), new Pair<>(5,-2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_small_7"), new Pair<>(5, -3))
    );

    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_JUNGLE_SMALL_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_jungle_small_1"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_jungle_small_2"), new Pair<>(5, -2))
    );

    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_JUNGLE_MEDIUM_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_jungle_medium_1"), new Pair<>(4, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_jungle_medium_2"), new Pair<>(4, 0))
    );

    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_OAK_MEDIUM_LIST = Map.ofEntries(
//            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_medium_1"), new Pair<>(4, 0)),
//            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_medium_2"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_medium_3"), new Pair<>(15, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_medium_4"), new Pair<>(15, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_medium_5"), new Pair<>(15, -2))
    );
    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_SPRUCE_SMALL_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_small_1"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_small_2"), new Pair<>(5, -3)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_small_3"), new Pair<>(5, 0))
    );
    public static final Map<ResourceLocation, Pair<Integer, Integer>> FANTASY_SPRUCE_MEDIUM_LIST = Map.ofEntries(
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_1"), new Pair<>(5, -2)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_2"), new Pair<>(5, -3)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_3"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_4"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_5"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_6"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_7"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_8"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_9"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_10"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_11"), new Pair<>(5, 0)),
            Map.entry(new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_medium_12"), new Pair<>(5, 0))
    );

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(FantasyTrees.MOD_ID, name));
    }

    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_ACACIA_SMALL = createKey("fantasy_acacia_small");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_BIRCH_SMALL  = createKey("fantasy_birch_small");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_BIRCH_MEDIUM  = createKey("fantasy_birch_medium");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_OAK_SMALL  = createKey("fantasy_oak_small");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_OAK_MEDIUM  = createKey("fantasy_oak_medium");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_JUNGLE_SMALL  = createKey("fantasy_jungle_small");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_JUNGLE_MEDIUM  = createKey("fantasy_jungle_medium");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_SPRUCE_SMALL  = createKey("fantasy_spruce_small");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_SPRUCE_MEDIUM  = createKey("fantasy_spruce_medium");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_FOREST_GRASS  = createKey("fantasy_forest_grass");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_VANILLA_FLOWERS  = createKey("fantasy_vanilla_flowers");
    public static final ResourceKey<ConfiguredFeature<?,?>> FANTASY_FLOWERS  = createKey("fantasy_flowers");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        register(context,
                FANTASY_ACACIA_SMALL,
                FeatureRegistry.FANTASY_ACACIA_SMALL.get(),
                new TreeConfiguration(FANTASY_ACACIA_SMALL_LIST));
        register(context,
                FANTASY_BIRCH_SMALL,
                FeatureRegistry.FANTASY_BIRCH_SMALL.get(),
                new TreeConfiguration(FANTASY_BIRCH_SMALL_LIST));
        register(context,
                FANTASY_BIRCH_MEDIUM,
                FeatureRegistry.FANTASY_BIRCH_MEDIUM.get(),
                new TreeConfiguration(FANTASY_BIRCH_MEDIUM_LIST));
        register(context,
                FANTASY_OAK_SMALL,
                FeatureRegistry.FANTASY_OAK_SMALL.get(),
                new TreeConfiguration(FANTASY_OAK_SMALL_LIST));
        register(context,
                FANTASY_OAK_MEDIUM,
                FeatureRegistry.FANTASY_OAK_MEDIUM.get(),
                new TreeConfiguration(FANTASY_OAK_MEDIUM_LIST));
        register(context,
                FANTASY_JUNGLE_SMALL,
                FeatureRegistry.FANTASY_JUNGLE_SMALL.get(),
                new TreeConfiguration(FANTASY_JUNGLE_SMALL_LIST));
        register(context,
                FANTASY_JUNGLE_MEDIUM,
                FeatureRegistry.FANTASY_JUNGLE_MEDIUM.get(),
                new TreeConfiguration(FANTASY_JUNGLE_MEDIUM_LIST));
        register(context,
                FANTASY_SPRUCE_SMALL,
                FeatureRegistry.FANTASY_SPRUCE_SMALL.get(),
                new TreeConfiguration(FANTASY_SPRUCE_SMALL_LIST));
        register(context,
                FANTASY_SPRUCE_MEDIUM,
                FeatureRegistry.FANTASY_SPRUCE_MEDIUM.get(),
                new TreeConfiguration(FANTASY_SPRUCE_MEDIUM_LIST));
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
