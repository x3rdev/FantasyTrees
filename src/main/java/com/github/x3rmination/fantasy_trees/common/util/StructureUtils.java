package com.github.x3rmination.fantasy_trees.common.util;

import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

public final class StructureUtils {

    private StructureUtils(){}

    //Keep them here if I need them

//    public static boolean isChunkAreaFlat(PieceGeneratorSupplier.Context<JigsawConfiguration> context, int chunkRadius, int tolerance) {
//        return isChunkAreaFlat(context.chunkPos(), context.chunkGenerator(), context.heightAccessor(), chunkRadius, tolerance);
//    }
//
//    public static boolean isChunkAreaFlat(FeaturePlaceContext<?> context, int chunkRadius, int tolerance) {
//        return isChunkAreaFlat(context.level().getChunk(context.origin()).getPos(), context.chunkGenerator(), context.level(), chunkRadius, tolerance);
//    }
//    public static boolean isChunkAreaFlat(ChunkPos origin, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor, int chunkRadius, int tolerance) {
//        int min = 256;
//        int max = 0;
//        for(int i = -chunkRadius; i < chunkRadius + 1; i+=2) {
//            for(int j = -chunkRadius; j < chunkRadius + 1; j+=2) {
//                ChunkPos chunkPos = new ChunkPos(origin.x + i, origin.z + j);
//                int[] range = guessSurfaceHeightRange(chunkPos, chunkGenerator, heightAccessor);
//                min = Math.min(range[0], min);
//                max = Math.max(range[1], max);
//                if(max - min > tolerance) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    // Slightly quicker way of estimating height. Should work fine most of the time with less performance impact
    public static int[] guessSurfaceHeightRange(ChunkPos chunkPos, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor) {
        int x = chunkPos.getMinBlockX();
        int z = chunkPos.getMinBlockZ();
        int min = 256;
        int max = 0;
        for(int i = 0; i < 15; i+=3) {
            int height = chunkGenerator.getBaseHeight(x + i, z + i, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor);
            min = Math.min(height, min);
            max = Math.max(height, max);
        }
        return new int[]{min, max};
    }

    public static boolean isAreaFlat(BlockPos blockPos, PieceGeneratorSupplier.Context<JigsawConfiguration> context, int radius, int tolerance) {
        return isAreaFlat(blockPos, context.chunkGenerator(), context.heightAccessor(), radius, tolerance);
    }

    public static boolean isAreaFlat(BlockPos blockPos, FeaturePlaceContext<?> context, int radius, int tolerance) {
        return isAreaFlat(blockPos, context.chunkGenerator(), context.level(), radius, tolerance);
    }

    public static boolean isAreaFlat(final BlockPos blockPos, final ChunkGenerator chunkGenerator, final LevelHeightAccessor levelHeightAccessor, int radius, int tolerance) {
        final int[] min = {blockPos.getY()};
        final int[] max = {blockPos.getY()};
        for(int i = -radius; i < radius; i++) {
            BlockPos checkPos = blockPos.offset(i, 0, i);
            int h = chunkGenerator.getFirstFreeHeight(checkPos.getX(), checkPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, levelHeightAccessor);
            min[0] = Math.min(min[0], h);
            max[0] = Math.max(max[0], h);
            if(Math.abs(min[0] - max[0]) > tolerance) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAreaDry(PieceGeneratorSupplier.Context<JigsawConfiguration> context, BlockPos origin, int radius) {
        return isAreaDry(origin, context.chunkGenerator(), context.heightAccessor(), radius);
    }

    public static boolean isAreaDry(FeaturePlaceContext<?> context, BlockPos origin, int radius) {
        return isAreaDry(origin, context.chunkGenerator(), context.level(), radius);
    }

    public static boolean isAreaDry(BlockPos blockPos, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor, int radius) {

        int firstOccupiedHeight = chunkGenerator.getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
        NoiseColumn firstColumn = chunkGenerator.getBaseColumn(blockPos.getX(), blockPos.getZ(), heightAccessor);
        if(!firstColumn.getBlock(firstOccupiedHeight).getFluidState().isEmpty()) {
            return false;
        }

        for(int i = -radius; i < radius; i+=3) {
            BlockPos checkPos = blockPos.offset(i, 0, i);
            int h = chunkGenerator.getFirstOccupiedHeight(checkPos.getX(), checkPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
            NoiseColumn column = chunkGenerator.getBaseColumn(checkPos.getX(), checkPos.getZ(), heightAccessor);
            if(!column.getBlock(h).getFluidState().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isChunkFlat(BlockPos pos, ChunkGenerator chunkGenerator, Climate.Parameter acceptableDepth, Climate.Parameter acceptableWeird) {
        double depth =  chunkGenerator.climateSampler().depth().compute(new DensityFunction.SinglePointContext(pos.getX(), pos.getY(), pos.getZ()));
        boolean f0 = depth >= Climate.unquantizeCoord(acceptableDepth.min()) && depth <= Climate.unquantizeCoord(acceptableDepth.max());

        double weirdness =  chunkGenerator.climateSampler().weirdness().compute(new DensityFunction.SinglePointContext(pos.getX(), pos.getY(), pos.getZ()));
        boolean f1 = weirdness >= Climate.unquantizeCoord(acceptableWeird.min()) && weirdness <= Climate.unquantizeCoord(acceptableWeird.max());
        return f0 && f1;
    }

    public static boolean isSuitableTreePos(BlockPos pos, FeaturePlaceContext<TreeConfiguration> context) {
        boolean f0 = context.level().getBlockState(pos.below()).is(BlockTags.DIRT);
        boolean f1 = !context.level().getBlockState(pos).isCollisionShapeFullBlock(context.level(), pos);
        return f0 && f1;
    }
}
