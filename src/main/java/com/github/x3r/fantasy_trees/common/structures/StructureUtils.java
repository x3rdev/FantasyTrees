package com.github.x3r.fantasy_trees.common.structures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jetbrains.annotations.NotNull;

public final class StructureUtils {

    private StructureUtils(){}

    public static boolean isAreaDry(@NotNull Structure.GenerationContext context, BlockPos origin, int radius) {
        return isAreaDry(origin, context.chunkGenerator(), context.heightAccessor(), radius, context);
    }

    public static boolean isAreaDry(BlockPos blockPos, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor, int radius, Structure.@NotNull GenerationContext context) {
        for(Direction direction : Direction.values()) {
            if(direction.getAxis().isHorizontal()) {
                BlockPos checkPos = blockPos.relative(direction, radius);
                int h = chunkGenerator.getFirstFreeHeight(checkPos.getX(), checkPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, context.randomState());
                NoiseColumn column = chunkGenerator.getBaseColumn(checkPos.getX(), checkPos.getZ(), heightAccessor, context.randomState());
                if(!column.getBlock(h).getFluidState().isEmpty()) {
                    return false;
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