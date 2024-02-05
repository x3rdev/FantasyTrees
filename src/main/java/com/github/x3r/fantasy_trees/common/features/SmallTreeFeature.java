package com.github.x3r.fantasy_trees.common.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class SmallTreeFeature extends FantasyTreeFeature {

    public SmallTreeFeature(Codec<TreeConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        TreeConfiguration treeConfiguration = context.config();
        StructureTemplateManager structuremanager = worldgenlevel.getLevel().getServer().getStructureManager();
        ResourceLocation resourceLocation = treeConfiguration.getRandomTree(treeConfiguration.trees, context.random());
        StructureTemplate structuretemplate = structuremanager.getOrCreate(resourceLocation);
        BlockPos center = context.origin().offset(structuretemplate.getSize().getX()/2, structuretemplate.getSize().getY()/2, structuretemplate.getSize().getZ()/2);
//        context.level().setBlock(center, Blocks.REDSTONE_BLOCK.defaultBlockState(), 4);
        StructurePlaceSettings settings = new StructurePlaceSettings().setRandom(context.random()).setRotationPivot(new BlockPos(structuretemplate.getSize().getX()/2, 0, structuretemplate.getSize().getZ()/2)).setRotation(Rotation.getRandom(context.random()));
        BlockPos placePos = new BlockPos(context.origin().getX(), center.getY() - 1 + getYOffset(treeConfiguration.trees, resourceLocation), context.origin().getZ());
        structuretemplate.placeInWorld(worldgenlevel, placePos, placePos, settings, context.random(), 4);
        return true;
    }
}
