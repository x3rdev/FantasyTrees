package com.github.x3r.fantasy_trees.common.data;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.blocks.*;
import com.github.x3r.fantasy_trees.registry.BlockItemRegistry;
import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FantasyItemTagProvider extends ItemTagsProvider {

    public FantasyItemTagProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, FantasyTrees.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        for(RegistryObject<Item> registryObject : BlockItemRegistry.BLOCK_ITEMS.getEntries()) {
            if(registryObject.get() instanceof BlockItem item) {
                if(item.getBlock() instanceof FantasyLogBlock) {
                    tag(ItemTags.LOGS).add(item);
                    tag(ItemTags.LOGS_THAT_BURN).add(item);
                }
                if(item.getBlock() instanceof FantasyLeavesBlock) {
                    tag(ItemTags.LEAVES).add(item);
                }
                if(item.getBlock() instanceof FantasyPlanksBlock) {
                    tag(ItemTags.PLANKS).add(item);
                }
                if(item.getBlock() instanceof StairBlock) {
                    tag(ItemTags.STAIRS).add(item);
                    tag(ItemTags.WOODEN_STAIRS).add(item);
                }
                if(item.getBlock() instanceof FantasyDoorBlock) {
                    tag(ItemTags.DOORS).add(item);
                    tag(ItemTags.WOODEN_DOORS).add(item);
                }
                if(item.getBlock() instanceof FantasyTrapDoorBlock) {
                    tag(ItemTags.TRAPDOORS).add(item);
                    tag(ItemTags.WOODEN_TRAPDOORS).add(item);
                }
                if(item.getBlock() instanceof FenceBlock) {
                    tag(ItemTags.FENCES).add(item);
                    tag(ItemTags.WOODEN_FENCES).add(item);
                }
                if(item.getBlock() instanceof FenceGateBlock) {
                    tag(ItemTags.FENCE_GATES).add(item);
                }
                if(item.getBlock() instanceof FantasySaplingBlock) {
                    tag(ItemTags.SAPLINGS).add(item);
                }
            }
        }

    }
}
