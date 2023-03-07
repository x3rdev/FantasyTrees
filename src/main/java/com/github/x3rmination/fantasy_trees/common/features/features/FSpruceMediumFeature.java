package com.github.x3rmination.fantasy_trees.common.features.features;

import com.github.x3rmination.fantasy_trees.common.features.configuration.FSpruceMediumConfiguration;
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

public class FSpruceMediumFeature extends Feature<FSpruceMediumConfiguration> {

    public FSpruceMediumFeature(Codec<FSpruceMediumConfiguration> pCodec) {
        super(pCodec);
    }

    public static boolean isFeatureChunk(FeaturePlaceContext<FSpruceMediumConfiguration> context) {
        BlockState topBlock = context.level().getBlockState(context.origin().below());
        return topBlock.is(BlockTags.DIRT);
    }
    @Override
    public boolean place(FeaturePlaceContext<FSpruceMediumConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        FSpruceMediumConfiguration treeConfiguration = pContext.config();
        StructureManager structuremanager = worldgenlevel.getLevel().getServer().getStructureManager();
        int i = pContext.random().nextInt(treeConfiguration.trees.size());
        StructureTemplate structuretemplate = structuremanager.getOrCreate(treeConfiguration.trees.get(i));
        int x = pContext.origin().getX() - (structuretemplate.getSize().getX() / 2);
        int z = pContext.origin().getZ() - (structuretemplate.getSize().getZ() / 2);
        int y = pContext.origin().getY();
        if(!isFeatureChunk(pContext)) {
            return false;
        }
        BlockPos placePos = new BlockPos(x, y + getYOffset(i), z);

        //for testing, if redstone does not spawn, the tree is in the right spot
        worldgenlevel.setBlock(pContext.origin(), Blocks.REDSTONE_BLOCK.defaultBlockState(), 4);

        structuretemplate.placeInWorld(worldgenlevel, placePos, placePos, new StructurePlaceSettings(), pContext.random(), 4);
        return true;
    }

    //Should be used for trees with roots in which case modifying the nbt file would not work
    public static int getYOffset(int tree) {
        return switch (tree) {
            case 1 -> -2;
            case 2, 5 -> -3;
            default -> 0;
        };
    }
}
