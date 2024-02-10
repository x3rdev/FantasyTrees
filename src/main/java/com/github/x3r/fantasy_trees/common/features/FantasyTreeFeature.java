package com.github.x3r.fantasy_trees.common.features;

import com.github.x3r.fantasy_trees.common.structures.StructureUtils;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class FantasyTreeFeature extends Feature<TreeConfiguration> {

    public FantasyTreeFeature(Codec<TreeConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        TreeConfiguration treeConfiguration = context.config();
        StructureTemplateManager structureTemplateManager = worldgenlevel.getLevel().getServer().getStructureManager();
        ResourceLocation resourceLocation = TreeConfiguration.getRandomTree(treeConfiguration.trees, context.random());
        StructureTemplate structureTemplate = structureTemplateManager.getOrCreate(resourceLocation);
        BlockPos pos = context.origin();
        Rotation rotation = Rotation.getRandom(context.random());
        BlockPos offsetPos = new BlockPos((structureTemplate.getSize().getX()/2), pos.getY(), (structureTemplate.getSize().getZ()/2));
        BlockPos centerPos = pos.offset(offsetPos.rotate(rotation));
        int y = context.chunkGenerator().getFirstOccupiedHeight(centerPos.getX(), centerPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.level(), context.level().getLevel().getChunkSource().randomState());
        centerPos = centerPos.atY(y + getYOffset(treeConfiguration.trees, resourceLocation));
        if(!isFeatureChunk(context, centerPos)) {
            return false;
        }
        BlockPos placePos = pos.atY(centerPos.getY());
        StructurePlaceSettings settings = new StructurePlaceSettings().setRandom(context.random()).setRotationPivot(BlockPos.ZERO).setRotation(rotation);
        structureTemplate.placeInWorld(worldgenlevel, placePos, placePos, settings, context.random(), 4);
        return true;
    }

    public boolean isFeatureChunk(@NotNull FeaturePlaceContext<TreeConfiguration> context, BlockPos pos) {
        if(!StructureUtils.isChunkFlat(pos, context.level().getLevel().getChunkSource().randomState().sampler(), Climate.Parameter.span(-0.3F, 0.3F), Climate.Parameter.span(-0.6F, 0.6F))) {
            return false;
        }
        if(!context.level().getBlockState(pos.above()).isAir() || !context.level().getBlockState(pos).isCollisionShapeFullBlock(context.level(), pos)) {
            return false;
        }
        if(!StructureUtils.isAreaDry(pos, context, 2)) {
            return false;
        }
        return true;
    }

    public int getYOffset(Map<ResourceLocation, Pair<Integer, Integer>> map, ResourceLocation tree) {
        return map.get(tree).getSecond();
    }
}
