package com.github.x3rmination.fantasy_trees.common.blocks;

import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class FantasyDoorBlock extends DoorBlock {
    public FantasyDoorBlock() {
        super(BlockBehaviour.Properties.of(BlockRegistry.FANTASY_WOOD)
                .strength(3.0F).sound(SoundType.WOOD).noOcclusion().lightLevel(value -> 7));
    }
}
