package com.github.x3r.fantasy_trees.common.blocks;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.material.PushReaction;

public class FantasySaplingBlock extends SaplingBlock {
    public FantasySaplingBlock(AbstractTreeGrower pTreeGrower) {
        super(pTreeGrower, Properties.of().pushReaction(PushReaction.DESTROY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
    }
}
