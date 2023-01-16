package com.github.x3rmination.fantasy_trees.common.blocks;

import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class FantasyLogBlock extends RotatedPillarBlock {

    public FantasyLogBlock() {
        super(BlockBehaviour.Properties.of(BlockRegistry.FANTASY_WOOD)
                .strength(2.0F).sound(SoundType.WOOD).lightLevel(value -> 7));
    }
}
