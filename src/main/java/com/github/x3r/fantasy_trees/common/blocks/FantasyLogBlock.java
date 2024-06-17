package com.github.x3r.fantasy_trees.common.blocks;

import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class FantasyLogBlock extends RotatedPillarBlock {

    public FantasyLogBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(2.0F).sound(SoundType.WOOD).lightLevel(BlockRegistry.lightLevel()));
    }
}
