package com.github.x3r.fantasy_trees.common.blocks.grower;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.FantasyTreesConfig;
import com.github.x3r.fantasy_trees.common.structures.StructureUtils;
import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import com.github.x3r.fantasy_trees.registry.StructureRegistry;
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

import java.util.List;

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
        ResourceLocation structure = getStructure(pattern.getSecond(), random);
        if(structure != null) {
            StructureUtils.placeStructure(getStructure(pattern.getSecond(), random), level, pos);
            destroyArea(level, pattern.getFirst());
        } else {
            return false;
        }
        return true;
    }

    protected ResourceLocation getStructure(int size, RandomSource random) {
        switch (this.name) {
            case "acacia":
                switch (size) {
                    case 3:
                        return randomStructure(StructureRegistry.ACACIA_LARGE_WEIGHTS, random);
                    case 1:
                        return randomStructure(StructureRegistry.ACACIA_SMALL_WEIGHTS, random);
                }
                break;
            case "birch":
                switch (size) {
                    case 3:
                        return randomStructure(StructureRegistry.BIRCH_LARGE_WEIGHTS, random);
                    case 2:
                        return randomStructure(StructureRegistry.BIRCH_MEDIUM_WEIGHTS, random);
                    case 1:
                        return randomStructure(StructureRegistry.BIRCH_SMALL_WEIGHTS, random);
                }
                break;
            case "dark_oak":
                switch (size) {
                    case 3:
                        return randomStructure(StructureRegistry.DARK_OAK_LARGE_WEIGHTS, random);
                }
                break;
            case "jungle":
                switch (size) {
                    case 3:
                        return randomStructure(StructureRegistry.JUNGLE_LARGE_WEIGHTS, random);
                    case 2:
                        return randomStructure(StructureRegistry.JUNGLE_MEDIUM_WEIGHTS, random);
                    case 1:
                        return randomStructure(StructureRegistry.JUNGLE_SMALL_WEIGHTS, random);
                }
                break;
            case "oak":
                switch (size) {
                    case 3:
                        return randomStructure(StructureRegistry.OAK_LARGE_WEIGHTS, random);
                    case 2:
                        return randomStructure(StructureRegistry.OAK_MEDIUM_WEIGHTS, random);
                    case 1:
                        return randomStructure(StructureRegistry.OAK_SMALL_WEIGHTS, random);
                }
                break;
            case "spruce":
                switch (size) {
                    case 3:
                        return randomStructure(StructureRegistry.SPRUCE_LARGE_WEIGHTS, random);
                    case 2:
                        return randomStructure(StructureRegistry.SPRUCE_MEDIUM_WEIGHTS, random);
                    case 1:
                        return randomStructure(StructureRegistry.SPRUCE_SMALL_WEIGHTS, random);
                }
                break;
        }
        return null;
    }

    protected ResourceLocation randomStructure(List<Pair<String, Integer>> list,  RandomSource random) {
        return new ResourceLocation(FantasyTrees.MOD_ID, list.get(random.nextInt(list.size())).getFirst());
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
