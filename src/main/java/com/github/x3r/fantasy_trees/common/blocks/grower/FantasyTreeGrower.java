package com.github.x3r.fantasy_trees.common.blocks.grower;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.FantasyTreesConfig;
import com.github.x3r.fantasy_trees.common.features.TreeConfiguration;
import com.github.x3r.fantasy_trees.common.util.StructureUtils;
import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import com.github.x3r.fantasy_trees.registry.ConfiguredFeatureRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class FantasyTreeGrower extends AbstractTreeGrower {
    private final String name;
    public FantasyTreeGrower(String name) {
        super();
        this.name = name;
    }

    @Override
    public boolean growTree(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random) {
        if(!FantasyTreesConfig.can_grow_fantasy_sapling.get()) {
            return false;
        }
        Pair<BlockPattern.BlockPatternMatch, Integer> pattern = getTreePattern(level, pos);
        ResourceLocation structure = getStructure(pattern.getSecond(), name, random);
        if(structure != null) {
            destroyArea(level, pattern.getFirst());
        } else {
            return false;
        }
        int offset = 0;
        if(pattern.getSecond() == 2) {
            offset = -3;
        }
        if(pattern.getSecond() == 3) {
            offset = -5;
        }
        return StructureUtils.placeStructure(structure, level, pos, offset);
    }

    protected static ResourceLocation getStructure(int size, String name, RandomSource random) {
        switch (size) {
            case 1 -> {
                if (name.equals("acacia")) {
                    return TreeConfiguration.getRandomTree(ConfiguredFeatureRegistry.FANTASY_ACACIA_SMALL_LIST, random);
                }
                if (name.equals("birch")) {
                    return TreeConfiguration.getRandomTree(ConfiguredFeatureRegistry.FANTASY_BIRCH_SMALL_LIST, random);
                }
                if (name.equals("oak")) {
                    return TreeConfiguration.getRandomTree(ConfiguredFeatureRegistry.FANTASY_OAK_SMALL_LIST, random);
                }
                if (name.equals("jungle")) {
                    return TreeConfiguration.getRandomTree(ConfiguredFeatureRegistry.FANTASY_JUNGLE_SMALL_LIST, random);
                }
                if (name.equals("spruce")) {
                    return TreeConfiguration.getRandomTree(ConfiguredFeatureRegistry.FANTASY_SPRUCE_SMALL_LIST, random);
                }
            }
            case 2 -> {
                if (name.equals("birch")) {
                    return TreeConfiguration.getRandomTree(ConfiguredFeatureRegistry.FANTASY_BIRCH_MEDIUM_LIST, random);
                }
                if (name.equals("oak")) {
                    return TreeConfiguration.getRandomTree(ConfiguredFeatureRegistry.FANTASY_OAK_MEDIUM_LIST, random);
                }
                if (name.equals("jungle")) {
                    return TreeConfiguration.getRandomTree(ConfiguredFeatureRegistry.FANTASY_JUNGLE_MEDIUM_LIST, random);
                }
                if (name.equals("spruce")) {
                    return TreeConfiguration.getRandomTree(ConfiguredFeatureRegistry.FANTASY_SPRUCE_MEDIUM_LIST, random);
                }
            }
            case 3 -> {
                if (name.equals("acacia")) {
                    return new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_acacia_large_" + (random.nextInt(3) + 1));
                }
                if (name.equals("birch")) {
                    return new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_birch_large_" + (1));
                }
                if (name.equals("dark_oak")) {
                    return new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_dark_oak_large_" + (random.nextInt(4) + 1));
                }
                if (name.equals("jungle")) {
                    return new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_jungle_large_" + (random.nextInt(3) + 1));
                }
                if (name.equals("oak")) {
                    return new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_oak_large_" + (random.nextInt(5) + 1));
                }
                if (name.equals("spruce")) {
                    return new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_spruce_large_" + (random.nextInt(3) + 1));
                }
            }
        }
        return null;
    }

    protected Pair<BlockPattern.BlockPatternMatch, Integer> getTreePattern(ServerLevel level, BlockPos pos) {
        Block sapling = BlockRegistry.SAPLINGS.get(this.name).get();
        BlockPattern.BlockPatternMatch medium = mediumTreePattern(sapling).find(level, pos);
        if (medium != null) {
            return new Pair<>(medium, 2);
        }
        BlockPattern.BlockPatternMatch large = largeTreePattern(sapling).find(level, pos);
        if (large != null) {
            return new Pair<>(large, 3);
        }
        return new Pair<>(null, 1);
    }
    private BlockPattern mediumTreePattern(Block sapling) {
        return BlockPatternBuilder.start()
                .aisle("~*~", "*^*", "~*~")
                .where('~', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.AIR)))
                .where('*', BlockInWorld.hasState(BlockStatePredicate.forBlock(sapling)))
                .where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.GOLD_BLOCK)))
                .build();
    }
    private BlockPattern largeTreePattern(Block sapling) {
        return BlockPatternBuilder.start()
                .aisle("***", "*^*", "***")
                .where('*', BlockInWorld.hasState(BlockStatePredicate.forBlock(sapling)))
                .where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.DIAMOND_BLOCK)))
                .build();
    }

    protected void destroyArea(ServerLevel level, BlockPattern.BlockPatternMatch patternMatch) {
        if(patternMatch != null) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    level.setBlock(patternMatch.getBlock(i, j, 0).getPos(), Blocks.AIR.defaultBlockState(), 18);
                }
            }
        }
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return null;
    }
}
