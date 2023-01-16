package com.github.x3rmination.fantasy_trees.common.blocks;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class FantasyLeavesBlock extends LeavesBlock {
    public FantasyLeavesBlock() {
        super(BlockBehaviour.Properties.of((new Material.Builder(MaterialColor.PLANT)).notSolidBlocking().destroyOnPush().build())
                .strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).isViewBlocking(Blocks::never).lightLevel(value -> 7));
    }
}
