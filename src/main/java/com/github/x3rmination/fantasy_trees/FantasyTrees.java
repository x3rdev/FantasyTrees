package com.github.x3rmination.fantasy_trees;

import com.github.x3rmination.fantasy_trees.client.ClientEvents;
import com.github.x3rmination.fantasy_trees.registry.BlockItemRegistry;
import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import com.github.x3rmination.fantasy_trees.registry.ItemRegistry;
import com.github.x3rmination.fantasy_trees.registry.StructureRegistry;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("fantasy_trees")
public class FantasyTrees {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "fantasy_trees";

    public FantasyTrees() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockRegistry.registerFantasyBlocks();
        BlockItemRegistry.BLOCK_ITEMS.register(modEventBus);
        BlockItemRegistry.registerFantasyBlockItems();
        ItemRegistry.ITEMS.register(modEventBus);
        ItemRegistry.registerFantasyItems();
        StructureRegistry.STRUCTURE_FEATURES.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}
