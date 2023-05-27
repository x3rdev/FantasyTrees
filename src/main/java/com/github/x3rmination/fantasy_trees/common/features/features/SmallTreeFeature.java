package com.github.x3rmination.fantasy_trees.common.features.features;

import com.github.x3rmination.fantasy_trees.common.features.configuration.TreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class SmallTreeFeature extends FantasyTreeFeature {

    public SmallTreeFeature(Codec<TreeConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean isFeaturePosition(FeaturePlaceContext<TreeConfiguration> context, BlockPos pos) {
        BlockState topBlock = context.level().getBlockState(pos.below());
        return topBlock.is(BlockTags.DIRT);
    }
    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        TreeConfiguration treeConfiguration = context.config();
        StructureManager structuremanager = worldgenlevel.getLevel().getServer().getStructureManager();
        ResourceLocation resourceLocation = treeConfiguration.getRandomTree(context.random());
        StructureTemplate structuretemplate = structuremanager.getOrCreate(resourceLocation);
        if(!isFeaturePosition(context, context.origin().offset(structuretemplate.getSize().getX()/2, 0, structuretemplate.getSize().getZ()/2))) {
            return false;
        }
        StructurePlaceSettings settings = new StructurePlaceSettings().setRandom(context.random()).setRotationPivot(new BlockPos(structuretemplate.getSize().getX()/2, 0, structuretemplate.getSize().getZ()/2)).setRotation(Rotation.getRandom(context.random()));
        BlockPos placePos = new BlockPos(context.origin().getX(), context.origin().getY() + getYOffset(treeConfiguration.trees, resourceLocation), context.origin().getZ());
        structuretemplate.placeInWorld(worldgenlevel, placePos, placePos, settings, context.random(), 4);
        return true;
    }
}