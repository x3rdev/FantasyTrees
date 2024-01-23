package com.github.x3r.fantasy_trees.common.blocks;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FantasyLogBlock extends RotatedPillarBlock {

    public FantasyLogBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(2.0F).sound(SoundType.WOOD).lightLevel(value -> 15));
    }


}
