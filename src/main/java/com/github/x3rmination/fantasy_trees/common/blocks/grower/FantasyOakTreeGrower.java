package com.github.x3rmination.fantasy_trees.common.blocks.grower;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import net.minecraft.resources.ResourceLocation;

public class FantasyOakTreeGrower extends FantasyTreeGrower{
    @Override
    protected ResourceLocation getStructure() {
        return new ResourceLocation(FantasyTrees.MOD_ID, "fantasy_dark_oak_large_1");
    }
}
