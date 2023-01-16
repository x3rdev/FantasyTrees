package com.github.x3rmination.fantasy_trees.common.datagen;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.common.blocks.FantasyLogBlock;
import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FantasyBlockStateProvider extends BlockStateProvider {

    public FantasyBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, FantasyTrees.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        System.out.println("but not here");
//        BlockRegistry.WOODS.forEach((name, blockRegistryObject) -> {
//            logBlock((FantasyLogBlock) blockRegistryObject.get());
//        });
        simpleBlock(BlockRegistry.TEST_BLOCK.get());
    }




}
