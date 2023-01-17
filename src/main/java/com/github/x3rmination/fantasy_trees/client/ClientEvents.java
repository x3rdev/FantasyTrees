package com.github.x3rmination.fantasy_trees.client;

import com.github.x3rmination.fantasy_trees.registry.BlockItemRegistry;
import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;
import java.util.List;

public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        BlockRegistry.SAPLINGS.forEach((s, blockRegistryObject) -> ItemBlockRenderTypes.setRenderLayer(blockRegistryObject.get(), RenderType.cutoutMipped()));
        BlockRegistry.DOORS.forEach((s, blockRegistryObject) -> ItemBlockRenderTypes.setRenderLayer(blockRegistryObject.get(), RenderType.cutoutMipped()));
    }

    @SubscribeEvent
    public static void blockColorHandler(ColorHandlerEvent.Block event) {
        BlockColors colors = event.getBlockColors();
        BlockRegistry.LEAVES.forEach((s, blockRegistryObject) -> {
            colors.register((blockState, tintGetter, blockPos, i) -> tintGetter != null && blockPos != null ? getBlockLeafColor(s, tintGetter, blockPos) : FoliageColor.getDefaultColor(), blockRegistryObject.get());
        });
    }

    @SubscribeEvent
    public static void itemColorHandler(ColorHandlerEvent.Item event) {
        ItemColors colors = event.getItemColors();
        BlockRegistry.LEAVES.forEach((s, blockRegistryObject) -> colors.register((stack, i) -> {
            BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, i);
        }, blockRegistryObject.get()));
    }

    private static int getBlockLeafColor(String name, BlockAndTintGetter p_92627_, BlockPos p_92628_) {
        if(name.equals("spruce")) {
            return FoliageColor.getEvergreenColor();
        }
        if(name.equals("birch")) {
            return FoliageColor.getBirchColor();
        }
        return BiomeColors.getAverageFoliageColor(p_92627_, p_92628_);
    }
}
