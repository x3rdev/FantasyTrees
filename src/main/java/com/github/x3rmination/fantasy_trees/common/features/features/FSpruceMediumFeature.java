package com.github.x3rmination.fantasy_trees.common.features.features;

import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import com.mojang.serialization.Codec;

public class FSpruceMediumFeature extends SmallTreeFeature {

    public FSpruceMediumFeature(Codec<TreeConfiguration> pCodec) {
        super(pCodec);
    }
    @Override
    public int getYOffset(int tree) {
        return switch (tree) {
            case 1 -> -2;
            case 2, 5 -> -3;
            default -> 0;
        };
    }
}
