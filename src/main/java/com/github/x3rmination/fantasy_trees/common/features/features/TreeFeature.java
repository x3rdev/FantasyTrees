package com.github.x3rmination.fantasy_trees.common.features.features;

import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import com.github.x3rmination.fantasy_trees.common.util.StructureUtils;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class TreeFeature extends Feature<TreeConfiguration> {

    public TreeFeature(Codec<TreeConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean isFeatureChunk(FeaturePlaceContext<TreeConfiguration> context) {
        BlockState topBlock = context.level().getBlockState(context.origin().below());
        return topBlock.is(BlockTags.DIRT) && StructureUtils.isChunkDry(context);
    }
    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        TreeConfiguration treeConfiguration = context.config();
        StructureManager structuremanager = worldgenlevel.getLevel().getServer().getStructureManager();
        int i = context.random().nextInt(treeConfiguration.trees.size());
        StructureTemplate structuretemplate = structuremanager.getOrCreate(treeConfiguration.trees.get(i));
        int x = context.origin().getX() - (structuretemplate.getSize().getX() / 2);
        int z = context.origin().getZ() - (structuretemplate.getSize().getZ() / 2);
        int y = context.origin().getY();
        if(!isFeatureChunk(context)) {
            return false;
        }
        BlockPos placePos = new BlockPos(x, y + getYOffset(i), z);

        //for testing, if redstone does not spawn, the tree is in the right spot
        worldgenlevel.setBlock(context.origin(), Blocks.REDSTONE_BLOCK.defaultBlockState(), 4);

        structuretemplate.placeInWorld(worldgenlevel, placePos, placePos, new StructurePlaceSettings(), context.random(), 4);
        return true;
    }
    public int getYOffset(int tree) {
        return switch (tree) {
            default -> 0;
        };
    }
}
