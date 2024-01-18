package com.github.x3r.fantasy_trees.registry;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.items.GlowingCoalItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FantasyTrees.MOD_ID);

    public static final RegistryObject<Item> GLOWING_COAL = ITEMS.register("glowing_coal",
            () -> new GlowingCoalItem(new Item.Properties()));
    public static void registerFantasyItems() {}

    public static class FantasyTreesItemTab {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FantasyTrees.MOD_ID);

        public static final RegistryObject<CreativeModeTab> MAIN = CREATIVE_TABS.register("main", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.fantasy_trees"))
                .icon(ItemRegistry.GLOWING_COAL.get().asItem()::getDefaultInstance)
                .displayItems((pParameters, pOutput) -> {
                    BlockRegistry.BLOCKS.getEntries().forEach(blockRegistryObject -> pOutput.accept(blockRegistryObject.get().asItem()));
                    pOutput.accept(GLOWING_COAL.get());
                })
                .build());
    }
}
