package com.github.x3r.fantasy_trees.common.structures;

import com.github.x3r.fantasy_trees.common.features.TreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jetbrains.annotations.NotNull;

public final class StructureUtils {

    private StructureUtils(){}

    public static boolean isAreaDry(BlockPos blockPos, @NotNull Structure.GenerationContext context, int radius, int depth) {
        return isAreaDry(blockPos, context.chunkGenerator(), context.heightAccessor(), radius, depth, context.randomState());
    }
    public static boolean isAreaDry(BlockPos blockPos, @NotNull FeaturePlaceContext<TreeConfiguration> context, int radius, int depth) {
        return isAreaDry(blockPos, context.chunkGenerator(), context.level(), radius, depth, context.level().getLevel().getChunkSource().randomState());
    }

    public static boolean isAreaDry(BlockPos blockPos, ChunkGenerator chunkGenerator, LevelHeightAccessor level, int radius, int depth, RandomState state) {
        for (int j = 0; j < radius; j++) {
            for(Direction direction : Direction.values()) {
                BlockPos checkPos = blockPos.relative(direction, j);
                NoiseColumn column = chunkGenerator.getBaseColumn(checkPos.getX(), checkPos.getZ(), level, state);
                if(direction.getAxis().isHorizontal()) {
                    for (int i = -depth; i < depth; i++) {
                        if(column.getBlock(blockPos.getY()-depth).getFluidState().isSource()) {
                            return false;
                        }
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