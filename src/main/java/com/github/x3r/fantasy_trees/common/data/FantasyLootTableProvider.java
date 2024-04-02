package com.github.x3r.fantasy_trees.common.data;

import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FantasyLootTableProvider extends LootTableProvider {

    public FantasyLootTableProvider(PackOutput pOutput) {
        super(pOutput, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(FantasyBlockLoot::new, LootContextParamSets.BLOCK)));
    }

    private static class FantasyBlockLoot extends BlockLootSubProvider {

        protected FantasyBlockLoot() {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            for (RegistryObject<Block> block : BlockRegistry.WOODS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.LOGS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.STRIPPED_WOODS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.STRIPPED_LOGS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> leaves : BlockRegistry.LEAVES.values()) {
                this.add(leaves.get(), (block -> {
                    String name = BuiltInRegistries.BLOCK.getKey(block).getPath().substring(8);
                    name = name.replace(name.substring(name.length() - 7), "");
                    return createLeavesDrops(block, BlockRegistry.SAPLINGS.get(name).get(), NORMAL_LEAVES_SAPLING_CHANCES);
                }));
            }
            for (RegistryObject<Block> block : BlockRegistry.PLANKS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.STAIRS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> door : BlockRegistry.DOORS.values()) {
                this.add(door.get(), block -> createDoorTable(door.get()));
            }
            for (RegistryObject<Block> block : BlockRegistry.TRAPDOORS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.FENCES.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.FENCE_GATES.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.CRAFTING_TABLES.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.SAPLINGS.values()) {
                this.dropSelf(block.get());
            }
            this.dropSelf(BlockRegistry.FANTASY_FLOWER.get());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
        }
    }
}