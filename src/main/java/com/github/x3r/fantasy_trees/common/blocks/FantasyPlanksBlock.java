package com.github.x3r.fantasy_trees.common.blocks;

import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class FantasyPlanksBlock extends Block {

    public FantasyPlanksBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(2.0F, 3.0F).sound(SoundType.WOOD).lightLevel(BlockRegistry.lightLevel()));
    }
}
