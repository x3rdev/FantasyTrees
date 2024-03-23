package com.github.x3r.fantasy_trees.common.blocks.grower;

import com.github.x3r.fantasy_trees.FantasyTreesConfig;
import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
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

public class FantasyTreeGrower extends AbstractTreeGrower {

    public static BlockPattern MEDIUM_TREE_PATTERN;
    public static BlockPattern LARGE_TREE_PATTERN;
    private final String name;
    public FantasyTreeGrower(String name) {
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
            //TODO add back the code that places the structure
            destroyArea(level, pattern.getFirst());
        } else {
            return false;
        }
        return true;
    }

    protected static ResourceLocation getStructure(int size, String name, RandomSource random) {
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
        if(MEDIUM_TREE_PATTERN == null) {
            MEDIUM_TREE_PATTERN = BlockPatternBuilder.start()
                    .aisle("~*~", "*^*", "~*~")
                    .where('~', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.AIR)))
                    .where('*', BlockInWorld.hasState(BlockStatePredicate.forBlock(sapling)))
                    .where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.GOLD_BLOCK)))
                    .build();
        }
        return MEDIUM_TREE_PATTERN;
    }
    private BlockPattern largeTreePattern(Block sapling) {
        if(LARGE_TREE_PATTERN == null) {
            LARGE_TREE_PATTERN = BlockPatternBuilder.start()
                    .aisle("***", "*^*", "***")
                    .where('*', BlockInWorld.hasState(BlockStatePredicate.forBlock(sapling)))
                    .where('^', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.DIAMOND_BLOCK)))
                    .build();
        }
        return LARGE_TREE_PATTERN;
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
