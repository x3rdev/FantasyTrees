package com.github.x3r.fantasy_trees.common.features;

import com.github.x3r.fantasy_trees.common.structures.StructureUtils;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class FantasyTreeFeature extends Feature<TreeConfiguration> {

    public FantasyTreeFeature(Codec<TreeConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> pContext) {
        return false;
    }

    public boolean isFeatureChunk(@NotNull FeaturePlaceContext<TreeConfiguration> context, BlockPos pos) {
        if(!StructureUtils.isChunkFlat(pos, context.level().getLevel().getChunkSource().randomState().sampler(), Climate.Parameter.span(-0.3F, 0.3F), Climate.Parameter.span(-0.6F, 0.6F))) {
            return false;
        }
        if(!context.level().getBlockState(pos.above()).isAir() || !context.level().getBlockState(pos).isCollisionShapeFullBlock(context.level(), pos)) {
            return false;
        }
        if(!StructureUtils.isAreaDry(pos, context, 2)) {
            return false;
        }
        return true;
    }

    public int getYOffset(Map<ResourceLocation, Pair<Integer, Integer>> map, ResourceLocation tree) {
        return map.get(tree).getSecond();
    }
}
