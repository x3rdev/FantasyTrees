package com.github.x3r.fantasy_trees.common.datagen;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.blocks.FantasyLeavesBlock;
import com.github.x3r.fantasy_trees.common.blocks.FantasyLogBlock;
import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class FantasyTagProvider extends BlockTagsProvider {

    public FantasyTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, FantasyTrees.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for(Block block : BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get).toList()) {
            if(block instanceof FantasyLeavesBlock) {
                tag(BlockTags.MINEABLE_WITH_HOE).add(block);
                tag(BlockTags.LEAVES).add(block);
            } else {
                tag(BlockTags.MINEABLE_WITH_AXE).add(block);
            }
            if(block instanceof FenceBlock) {
                tag(BlockTags.FENCES).add(block);
                tag(BlockTags.WOODEN_FENCES).add(block);
            }
            if(block instanceof FantasyLogBlock fantasyLogBlock) {
                tag(BlockTags.LOGS).add(fantasyLogBlock);
                if(fantasyLogBlock.getRegistryName().getPath().equals("fantasy_jungle_log")) {
                    tag(BlockTags.JUNGLE_LOGS).add(fantasyLogBlock);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Fantasy Trees Block Tags";
    }
}
