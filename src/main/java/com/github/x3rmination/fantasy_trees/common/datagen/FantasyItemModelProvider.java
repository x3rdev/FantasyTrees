package com.github.x3rmination.fantasy_trees.common.datagen;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.registry.BlockItemRegistry;
import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FantasyItemModelProvider extends ItemModelProvider {

    public FantasyItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, FantasyTrees.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
