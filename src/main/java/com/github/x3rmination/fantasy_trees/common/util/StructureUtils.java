package com.github.x3rmination.fantasy_trees.common.util;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.FantasyTreesConfig;
import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class StructureUtils {

    private StructureUtils(){}

    public static boolean isAreaDry(PieceGeneratorSupplier.Context<JigsawConfiguration> context, BlockPos origin, int radius) {
        return isAreaDry(origin, context.chunkGenerator(), context.heightAccessor(), radius);
    }

    public static boolean isAreaDry(FeaturePlaceContext<?> context, BlockPos origin, int radius) {
        return isAreaDry(origin, context.chunkGenerator(), context.level(), radius);
    }

    public static boolean isAreaDry(BlockPos blockPos, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor, int radius) {
        for(Direction direction : Direction.values()) {
            if(direction.getAxis().isHorizontal()) {
                BlockPos checkPos = blockPos.relative(direction, radius);
                int h = chunkGenerator.getFirstOccupiedHeight(checkPos.getX(), checkPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
                NoiseColumn column = chunkGenerator.getBaseColumn(checkPos.getX(), checkPos.getZ(), heightAccessor);
                if(!column.getBlock(h).getFluidState().isEmpty()) {
                    return false;
                }
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

    public static boolean placeStructure(ResourceLocation resourceLocation, ServerLevel level, BlockPos pos, int offset) {
        if(resourceLocation != null && level.getStructureManager().get(resourceLocation).isPresent()) {
            StructureTemplate structuretemplate = level.getStructureManager().get(resourceLocation).get();
            FantasyTrees.LOGGER.info(Arrays.deepToString(StructureTemplate.class.getFields()));
            FantasyTrees.LOGGER.info(Arrays.deepToString(StructureTemplate.class.getDeclaredFields()));
            try {
                Field f = ObfuscationReflectionHelper.findField(StructureTemplate.class, "f_74482_");
                f.setAccessible(true);
                List<StructureTemplate.Palette> paletteList = (List<StructureTemplate.Palette>) f.get(structuretemplate);
                List<StructureTemplate.StructureBlockInfo> blocks = new ArrayList<>();
                paletteList.forEach(palette -> blocks.addAll(palette.blocks()));
                for(StructureTemplate.StructureBlockInfo info : blocks) {
                    Scheduler.schedule(() -> {
                        BlockPos placePos = pos.offset(info.pos).offset(-(structuretemplate.getSize().getX()/2), offset, -(structuretemplate.getSize().getZ()/2));
                        if(FantasyTreesConfig.override_blocks.get()) {
                            level.setBlock(placePos, info.state, 18);
                        } else {
                            if(level.getBlockState(placePos).isAir()) {
                                level.setBlock(placePos, info.state, 18);
                            }
                        }
                    }, info.pos.getY() * FantasyTreesConfig.growth_delay.get());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
