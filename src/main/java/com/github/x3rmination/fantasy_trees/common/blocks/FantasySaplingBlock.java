package com.github.x3rmination.fantasy_trees.common.blocks;

import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class FantasySaplingBlock extends SaplingBlock {
    public FantasySaplingBlock(AbstractTreeGrower pTreeGrower) {
        super(pTreeGrower, BlockBehaviour.Properties.of((new Material.Builder(MaterialColor.PLANT)).noCollider().notSolidBlocking().nonSolid().destroyOnPush().build()).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
    }
}
