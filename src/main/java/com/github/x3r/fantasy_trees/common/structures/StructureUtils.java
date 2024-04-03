package com.github.x3r.fantasy_trees.common.structures;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.RandomState;

public final class StructureUtils {

    private StructureUtils(){}

    public static boolean isAreaDry(BlockPos blockPos, ChunkGenerator chunkGenerator, LevelHeightAccessor level, int radius, RandomState state) {
        int height = 2;
        for (int i = -radius; i < radius; i++) {
            for (int j = -radius; j < radius; j++) {
                NoiseColumn column = chunkGenerator.getBaseColumn(blockPos.getX()+i, blockPos.getZ()+j, level, state);
                for (int k = 0; k < height; k++) {
                    int y = blockPos.getY() + k + FantasyTreeStructure.TREE_OFFSET;
                    BlockState block = column.getBlock(y);
                    if(!block.getFluidState().isEmpty()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static boolean isChunkFlat(BlockPos pos, Climate.Sampler sampler, Climate.Parameter acceptableDepth, Climate.Parameter acceptableWeird) {
        double depth =  sampler.depth().compute(new DensityFunction.SinglePointContext(pos.getX(), pos.getY(), pos.getZ()));
        boolean f0 = depth >= Climate.unquantizeCoord(acceptableDepth.min()) && depth <= Climate.unquantizeCoord(acceptableDepth.max());

        double weirdness = sampler.weirdness().compute(new DensityFunction.SinglePointContext(pos.getX(), pos.getY(), pos.getZ()));
        boolean f1 = weirdness >= Climate.unquantizeCoord(acceptableWeird.min()) && weirdness <= Climate.unquantizeCoord(acceptableWeird.max());
        return f0 && f1;
    }
}