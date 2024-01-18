package com.github.x3r.fantasy_trees.common.structures;

import com.github.x3r.fantasy_trees.FantasyTreesConfig;
import com.github.x3r.fantasy_trees.common.features.TreeConfiguration;
import com.github.x3r.fantasy_trees.common.util.Scheduler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class StructureUtils {

    private StructureUtils(){}

    public static boolean isAreaDry(@NotNull Structure.GenerationContext context, BlockPos origin, int radius) {
        return isAreaDry(origin, context.chunkGenerator(), context.heightAccessor(), radius, context);
    }

    public static boolean isAreaDry(BlockPos blockPos, ChunkGenerator chunkGenerator, LevelHeightAccessor heightAccessor, int radius, Structure.@NotNull GenerationContext context) {
        for(Direction direction : Direction.values()) {
            if(direction.getAxis().isHorizontal()) {
                BlockPos checkPos = blockPos.relative(direction, radius);
                int h = chunkGenerator.getFirstOccupiedHeight(checkPos.getX(), checkPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, context.randomState());
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

    public static boolean isSuitableTreePos(BlockPos pos, FeaturePlaceContext<TreeConfiguration> context) {
        boolean f0 = context.level().getBlockState(pos.below()).is(BlockTags.DIRT);
        boolean f1 = !context.level().getBlockState(pos).isCollisionShapeFullBlock(context.level(), pos);
        return f0 && f1;
    }

    public static boolean placeStructure(ResourceLocation resourceLocation, ServerLevel level, BlockPos pos, int offset) {
        if(resourceLocation != null && level.getStructureManager().get(resourceLocation).isPresent()) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 18);
            StructureTemplate structuretemplate = level.getStructureManager().get(resourceLocation).get();
            try {
                Field f = ObfuscationReflectionHelper.findField(StructureTemplate.class, "f_74482_");
                f.setAccessible(true);
                List<StructureTemplate.Palette> paletteList = (List<StructureTemplate.Palette>) f.get(structuretemplate);
                List<StructureTemplate.StructureBlockInfo> blocks = new ArrayList<>();
                List<StructureTemplate.StructureBlockInfo> leaves = new ArrayList<>();
                List<StructureTemplate.StructureBlockInfo> vines = new ArrayList<>();
                paletteList.forEach(palette -> palette.blocks().forEach(structureBlockInfo -> {
                    if(structureBlockInfo.state().getBlock() instanceof VineBlock) {
                        vines.add(structureBlockInfo);
                    } else if(structureBlockInfo.state().getBlock() instanceof LeavesBlock) {
                        leaves.add(structureBlockInfo);
                    } else {
                        blocks.add(structureBlockInfo);
                    }
                }));
                int i = getMaxHeight(paletteList) + 1;
                for(StructureTemplate.StructureBlockInfo info : blocks) {
                    Scheduler.schedule(() -> placeStructureBlock(info, structuretemplate, level, pos, offset), info.pos().getY() * FantasyTreesConfig.growth_delay.get());
                }
                for (StructureTemplate.StructureBlockInfo info : leaves) {
                    Scheduler.schedule(() -> placeStructureBlock(info, structuretemplate, level, pos, offset), (2 * i - info.pos().getY()) * FantasyTreesConfig.growth_delay.get());
                }
                for (StructureTemplate.StructureBlockInfo info : vines) {
                    Scheduler.schedule(() -> placeStructureBlock(info, structuretemplate, level, pos, offset), (int) ((2.5 * i - info.pos().getY()) * FantasyTreesConfig.growth_delay.get()));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    private static void placeStructureBlock(StructureTemplate.StructureBlockInfo info, StructureTemplate structuretemplate, ServerLevel level, BlockPos pos, int offset) {
        BlockPos placePos = pos.offset(info.pos()).offset(-(structuretemplate.getSize().getX() / 2), offset, -(structuretemplate.getSize().getZ() / 2));
        if (FantasyTreesConfig.override_blocks.get()) {
            level.setBlock(placePos, info.state(), 18);
        } else {
            if (level.getBlockState(placePos).isAir()) {
                level.setBlock(placePos, info.state(), 18);
            }
        }
    }

    private static int getMaxHeight(List<StructureTemplate.Palette> paletteList) {
        int max = 0;
        for (StructureTemplate.Palette palette : paletteList) {
            for (StructureTemplate.StructureBlockInfo block : palette.blocks()) {
                max = Math.max(max, block.pos().getY());
            }
        }
        return max;
    }
}