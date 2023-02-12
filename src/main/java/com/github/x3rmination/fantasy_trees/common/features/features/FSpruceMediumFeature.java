package com.github.x3rmination.fantasy_trees.common.features.features;

import com.github.x3rmination.fantasy_trees.common.features.configuration.FSpruceMediumConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.FossilFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class FSpruceMediumFeature extends Feature<FSpruceMediumConfiguration> {

    public FSpruceMediumFeature(Codec<FSpruceMediumConfiguration> pCodec) {
        super(pCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<FSpruceMediumConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        FSpruceMediumConfiguration treeConfiguration = pContext.config();
        StructureManager structuremanager = worldgenlevel.getLevel().getServer().getStructureManager();
        int i = pContext.random().nextInt(treeConfiguration.trees.size());
        StructureTemplate structuretemplate = structuremanager.getOrCreate(treeConfiguration.trees.get(i));
        BlockPos placePos = new BlockPos(pContext.origin().getX() - (structuretemplate.getSize().getX() / 2), pContext.origin().getY(), pContext.origin().getZ() - (structuretemplate.getSize().getZ() / 2));
        structuretemplate.placeInWorld(worldgenlevel, placePos, placePos, new StructurePlaceSettings(), pContext.random(), 4);
        return true;
    }
}
