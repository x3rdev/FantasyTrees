package com.github.x3r.fantasy_trees.common.structures;

import com.github.x3r.fantasy_trees.FantasyTreesConfig;
import com.github.x3r.fantasy_trees.common.util.Scheduler;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class StructureUtils {

    private StructureUtils(){}

    public static boolean isVolumeDry(BlockPos blockPos, ChunkGenerator chunkGenerator, LevelHeightAccessor level, int radius, RandomState state) {
        int height = 4;
        for (int i = -radius; i < radius; i++) {
            for (int j = -radius; j < radius; j++) {
                NoiseColumn column = chunkGenerator.getBaseColumn(blockPos.getX()+i, blockPos.getZ()+j, level, state);
                for (int k = -height; k < height; k++) {
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
    public static boolean placeStructure(ResourceLocation resourceLocation, ServerLevel level, BlockPos pos) {
        if(resourceLocation != null && level.getStructureManager().get(resourceLocation).isPresent()) {
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
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
                    Scheduler.schedule(() -> placeStructureBlock(info, structuretemplate, level, pos), info.pos().getY() * FantasyTreesConfig.growth_delay.get());
                }
                for (StructureTemplate.StructureBlockInfo info : leaves) {
                    Scheduler.schedule(() -> placeStructureBlock(info, structuretemplate, level, pos), (2 * i - info.pos().getY()) * FantasyTreesConfig.growth_delay.get());
                }
                for (StructureTemplate.StructureBlockInfo info : vines) {
                    Scheduler.schedule(() -> placeStructureBlock(info, structuretemplate, level, pos), (int) ((2.5 * i - info.pos().getY()) * FantasyTreesConfig.growth_delay.get()));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    private static void placeStructureBlock(StructureTemplate.StructureBlockInfo info, StructureTemplate structuretemplate, ServerLevel level, BlockPos pos) {
        BlockPos placePos = pos.offset(info.pos()).offset(-(structuretemplate.getSize().getX() / 2), 0, -(structuretemplate.getSize().getZ() / 2));
        if (FantasyTreesConfig.override_blocks.get()) {
            level.setBlockAndUpdate(placePos, info.state());
        } else {
            if (level.getBlockState(placePos).isAir()) {
                level.setBlockAndUpdate(placePos, info.state());
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