package com.github.x3rmination.fantasy_trees.registry;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyTrees.MOD_ID);

    public static void registerFantasyItems() {

    }

    public static class ModItemTab extends CreativeModeTab {
        public static final ModItemTab instance = new ModItemTab(CreativeModeTab.TABS.length, FantasyTrees.MOD_ID);
        private ModItemTab(int index, String tabName) {
            super(index, tabName);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.APPLE);
        }

    }
}
