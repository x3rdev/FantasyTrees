package com.github.x3r.fantasy_trees.common.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class MediumTreeFeature extends FantasyTreeFeature {

    public MediumTreeFeature(Codec<TreeConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        TreeConfiguration treeConfiguration = context.config();
        StructureTemplateManager structureTemplateManager = worldgenlevel.getLevel().getServer().getStructureManager();
        ResourceLocation resourceLocation = TreeConfiguration.getRandomTree(treeConfiguration.trees, context.random());
        if(resourceLocation == null) {
            return false;
        }
        StructureTemplate structureTemplate = structureTemplateManager.getOrCreate(resourceLocation);
        BlockPos pos = context.origin();
        Rotation rotation = Rotation.getRandom(context.random());
        BlockPos centerPos = pos.offset(new BlockPos((structureTemplate.getSize().getX()/2), pos.getY(), (structureTemplate.getSize().getZ()/2)));
        int y = context.chunkGenerator().getFirstOccupiedHeight(centerPos.getX(), centerPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.level(), context.level().getLevel().getChunkSource().randomState());
        centerPos = centerPos.atY(y - 5 + getYOffset(treeConfiguration.trees, resourceLocation));
        if(!isFeatureChunk(context, centerPos)) {
            return false;
        }
        StructurePlaceSettings settings = new StructurePlaceSettings().setRandom(context.random()).setRotationPivot(new BlockPos(structureTemplate.getSize().getX()/2, 0, structureTemplate.getSize().getZ()/2)).setRotation(rotation);
        structureTemplate.placeInWorld(worldgenlevel, pos.atY(centerPos.getY()), pos.atY(centerPos.getY()), settings, context.random(), 4);
        return true;
    }
}
