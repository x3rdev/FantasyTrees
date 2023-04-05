package com.github.x3rmination.fantasy_trees.common.features.features;

import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import com.mojang.serialization.Codec;

public class FOakSmallFeature extends SmallTreeFeature {
    public FOakSmallFeature(Codec<TreeConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public int getYOffset(int tree) {
        return switch (tree) {
            case 1 -> -2;
            case 2 -> -2;
            case 3 -> -3;
            case 4 -> -5;
            case 5 -> 0;
            case 6 -> -3;
            case 7 -> -3;
            case 8 -> -2;
            default -> 0;
        };
    }
}
