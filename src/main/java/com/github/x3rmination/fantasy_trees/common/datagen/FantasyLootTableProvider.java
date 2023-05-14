package com.github.x3rmination.fantasy_trees.common.datagen;

import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FantasyLootTableProvider extends LootTableProvider {

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>>
            loot_tables = ImmutableList.of(Pair.of(FantasyBlockLoot::new, LootContextParamSets.BLOCK));

    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    public FantasyLootTableProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationTracker) {
        map.forEach((resourceLocation, lootTable) -> LootTables.validate(validationTracker, resourceLocation, lootTable));
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return loot_tables;
    }

    private class FantasyBlockLoot extends BlockLoot{
        @Override
        protected void addTables () {
            for (RegistryObject<Block> block : BlockRegistry.WOODS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.LOGS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> block : BlockRegistry.STRIPPED_LOGS.values()) {
                this.dropSelf(block.get());
            }
            for (RegistryObject<Block> leaves : BlockRegistry.LEAVES.values()) {
                this.add(leaves.get(), (block -> {
                    String name = block.getRegistryName().getPath().substring(8);
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
        }

        @Override
        protected Iterable<Block> getKnownBlocks () {
            return BlockRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
        }
    }
}
