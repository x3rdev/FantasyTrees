package com.github.x3r.fantasy_trees.common.blocks;

import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class FantasyDoorBlock extends DoorBlock {
    public FantasyDoorBlock(BlockSetType type) {
        super(BlockBehaviour.Properties.of()
                .strength(3.0F).sound(SoundType.WOOD).noOcclusion().lightLevel(value -> 7), type);
    }
}
