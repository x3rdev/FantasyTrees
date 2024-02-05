package com.github.x3r.fantasy_trees.common.blocks;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class FantasyTrapDoorBlock extends TrapDoorBlock {
    public FantasyTrapDoorBlock(BlockSetType type) {
        super(BlockBehaviour.Properties.of()
                .strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel(value -> 7), type);
    }


}
