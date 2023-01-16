package com.github.x3rmination.fantasy_trees.registry;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockItemRegistry {

    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyTrees.MOD_ID);
    public static void registerFantasyBlockItems() {
        BlockRegistry.BLOCKS.getEntries().forEach(blockRegistryObject -> BLOCK_ITEMS.register(blockRegistryObject.getId().getPath(), () -> new BlockItem(blockRegistryObject.get(), (new Item.Properties()).tab(ItemRegistry.ModItemTab.instance))));
    }


}
