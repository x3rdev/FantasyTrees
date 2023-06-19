package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.items.GlowingCoalItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyTrees.MOD_ID);

    public static final RegistryObject<Item> GLOWING_COAL = ITEMS.register("glowing_coal",
            () -> new GlowingCoalItem(new Item.Properties().tab(FantasyTreesItemTab.instance)));
    public static void registerFantasyItems() {

    }

    public static class FantasyTreesItemTab extends CreativeModeTab {
        public static final FantasyTreesItemTab instance = new FantasyTreesItemTab(CreativeModeTab.TABS.length, FantasyTrees.MOD_ID);
        private FantasyTreesItemTab(int index, String tabName) {
            super(index, tabName);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.GLOWING_COAL.get());
        }

    }
}
