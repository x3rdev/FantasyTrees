package com.github.x3rmination.fantasy_trees.common.features.features;

import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.Map;

public class FantasyTreeFeature extends Feature<TreeConfiguration> {

    public FantasyTreeFeature(Codec<TreeConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> pContext) {
        return false;
    }

    public int getYOffset(Map<ResourceLocation, Pair<Integer, Integer>> map, ResourceLocation tree) {
        return map.get(tree).getSecond();
    }
}
