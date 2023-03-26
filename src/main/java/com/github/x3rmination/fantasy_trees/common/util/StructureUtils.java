package com.github.x3rmination.fantasy_trees.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

public final class StructureUtils {

    private StructureUtils(){}

    public static boolean isChunkAreaFlat(PieceGeneratorSupplier.Context<JigsawConfiguration> context, int chunkRadius, int tolerance) {
        return isChunkAreaFlat(context.chunkPos(), context.chunkGenerator(), context.heightAccessor(), chunkRadius, tolerance);
    }

    public static boolean isChunkAreaFlat(FeaturePlaceContext<?> context, int chunkRadius, int tolerance) {
        return isChunkAreaFlat(context.level().getChunk(context.origin()).getPos(), context.chunkGenerator(), context.level(), chunkRadius, tolerance);
    }
    public static boolean isChunkAreaFlat(ChunkPos origin, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor, int chunkRadius, int tolerance) {
        int min = 256;
        int max = 0;
        for(int i = -chunkRadius; i < chunkRadius; i+=2) {
            for(int j = -chunkRadius; j < chunkRadius; j+=2) {
                ChunkPos chunkPos = new ChunkPos(origin.x + i, origin.z + j);
                int[] range = guessSurfaceHeightRange(chunkPos, chunkGenerator, heightAccessor);
                min = Math.min(range[0], min);
                max = Math.max(range[1], max);
                if(max - min > tolerance) {
                    return false;
                }
            }
        }
        return true;
    }

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

    public static boolean isChunkDry(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        return isChunkDry(context.chunkPos().getWorldPosition(), context.chunkGenerator(), context.heightAccessor());
    }

    public static boolean isChunkDry(FeaturePlaceContext<?> context) {
        return isChunkDry(context.origin(), context.chunkGenerator(), context.level());
    }

    public static boolean isChunkDry(BlockPos origin, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor) {
        for(int i = 0; i < 15; i+=2) {
            int landHeight = chunkGenerator.getFirstOccupiedHeight(origin.getX()+i, origin.getZ()+i, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
            NoiseColumn column = chunkGenerator.getBaseColumn(origin.getX()+i, origin.getZ()+i, heightAccessor);
            BlockState topBlock = column.getBlock(landHeight);
            if(!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }
        for(int i = 0; i < 15; i+=2) {
            int landHeight = chunkGenerator.getFirstOccupiedHeight(origin.getX()+15-i, origin.getZ()+15-i, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
            NoiseColumn column = chunkGenerator.getBaseColumn(origin.getX()+i, origin.getZ()+i, heightAccessor);
            BlockState topBlock = column.getBlock(landHeight);
            if(!topBlock.getFluidState().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
