package com.github.x3r.fantasy_trees.common.blocks;

import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FantasyTrapDoorBlock extends TrapDoorBlock {
    public FantasyTrapDoorBlock() {
        super(BlockBehaviour.Properties.of(BlockRegistry.FANTASY_WOOD)
                .strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel(value -> 7));
    }


}
