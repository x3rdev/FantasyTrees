package com.github.x3r.fantasy_trees.common.data;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FantasyBlockTagProvider extends BlockTagsProvider {

    public FantasyBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FantasyTrees.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        for (RegistryObject<Block> block : BlockRegistry.WOODS.values()) {
            tag(BlockTags.LOGS_THAT_BURN).add(block.get());
            tag(BlockTags.PARROTS_SPAWNABLE_ON).add(block.get());
            tag(BlockTags.LOGS).add(block.get());
            tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(block.get());
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.LOGS.values()) {
            tag(BlockTags.LOGS_THAT_BURN).add(block.get());
            tag(BlockTags.PARROTS_SPAWNABLE_ON).add(block.get());
            tag(BlockTags.LOGS).add(block.get());
            tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(block.get());
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.STRIPPED_WOODS.values()) {
            tag(BlockTags.LOGS_THAT_BURN).add(block.get());
            tag(BlockTags.PARROTS_SPAWNABLE_ON).add(block.get());
            tag(BlockTags.LOGS).add(block.get());
            tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(block.get());
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.STRIPPED_LOGS.values()) {
            tag(BlockTags.LOGS_THAT_BURN).add(block.get());
            tag(BlockTags.PARROTS_SPAWNABLE_ON).add(block.get());
            tag(BlockTags.LOGS).add(block.get());
            tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(block.get());
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.LEAVES.values()) {
            tag(BlockTags.REPLACEABLE_BY_TREES).add(block.get());
            tag(BlockTags.PARROTS_SPAWNABLE_ON).add(block.get());
            tag(BlockTags.MINEABLE_WITH_HOE).add(block.get());
            tag(BlockTags.SWORD_EFFICIENT).add(block.get());
            tag(BlockTags.LEAVES).add(block.get());
            tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.PLANKS.values()) {
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(BlockTags.PLANKS).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.STAIRS.values()) {
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(BlockTags.WOODEN_STAIRS).add(block.get());
            tag(BlockTags.STAIRS).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.DOORS.values()) {
            tag(BlockTags.DOORS).add(block.get());
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(BlockTags.WOODEN_DOORS).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.FENCES.values()) {
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(BlockTags.FENCES).add(block.get());
            tag(Tags.Blocks.FENCES).add(block.get());
            tag(Tags.Blocks.FENCES_WOODEN).add(block.get());
            tag(BlockTags.WOODEN_FENCES).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.FENCE_GATES.values()) {
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(Tags.Blocks.FENCE_GATES_WOODEN).add(block.get());
            tag(Tags.Blocks.FENCE_GATES).add(block.get());
            tag(BlockTags.FENCE_GATES).add(block.get());
            tag(BlockTags.UNSTABLE_BOTTOM_CENTER).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.CRAFTING_TABLES.values()) {
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.SAPLINGS.values()) {
            tag(BlockTags.SWORD_EFFICIENT).add(block.get());
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(BlockTags.SAPLINGS).add(block.get());
        }
        for (RegistryObject<Block> block : BlockRegistry.TRAPDOORS.values()) {
            tag(BlockTags.TRAPDOORS).add(block.get());
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(BlockTags.WOODEN_TRAPDOORS).add(block.get());
        }

    }

    @Override
    public String getName() {
        return "Fantasy Trees Block Tags";
    }
}
