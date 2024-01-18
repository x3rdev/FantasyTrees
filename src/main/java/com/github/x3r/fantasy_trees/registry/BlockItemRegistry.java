package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockItemRegistry {

    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyTrees.MOD_ID);
    public static void registerFantasyBlockItems() {
        BlockRegistry.BLOCKS.getEntries().forEach(blockRegistryObject -> BLOCK_ITEMS.register(blockRegistryObject.getId().getPath(), () -> new BlockItem(blockRegistryObject.get(), (new Item.Properties()))));
    }
}
