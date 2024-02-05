package com.github.x3r.fantasy_trees.client;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.registry.BlockRegistry;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = FantasyTrees.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        BlockRegistry.SAPLINGS.forEach((s, blockRegistryObject) -> ItemBlockRenderTypes.setRenderLayer(blockRegistryObject.get(), RenderType.cutoutMipped()));
        BlockRegistry.DOORS.forEach((s, blockRegistryObject) -> ItemBlockRenderTypes.setRenderLayer(blockRegistryObject.get(), RenderType.cutoutMipped()));
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.FANTASY_FLOWER.get(), RenderType.cutoutMipped());
    }

    @SubscribeEvent
    public static void blockColorHandler(RegisterColorHandlersEvent.Block event) {
        BlockColors colors = event.getBlockColors();
        BlockRegistry.LEAVES.forEach((s, blockRegistryObject) -> {
            colors.register((blockState, tintGetter, blockPos, i) -> tintGetter != null && blockPos != null ? getBlockLeafColor(s, tintGetter, blockPos) : FoliageColor.getDefaultColor(), blockRegistryObject.get());
        });
    }

    @SubscribeEvent
    public static void itemColorHandler(RegisterColorHandlersEvent.Item event) {
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
