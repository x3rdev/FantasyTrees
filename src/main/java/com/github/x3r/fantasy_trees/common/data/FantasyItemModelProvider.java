package com.github.x3r.fantasy_trees.common.data;

import com.github.x3r.fantasy_trees.FantasyTrees;
import com.github.x3r.fantasy_trees.common.blocks.FantasyDoorBlock;
import com.github.x3r.fantasy_trees.common.blocks.FantasySaplingBlock;
import com.github.x3r.fantasy_trees.common.blocks.FantasyTrapDoorBlock;
import com.github.x3r.fantasy_trees.registry.BlockItemRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FantasyItemModelProvider extends ItemModelProvider {

    public FantasyItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FantasyTrees.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        BlockItemRegistry.BLOCK_ITEMS.getEntries().forEach(itemRegistryObject -> {
            if(((BlockItem) itemRegistryObject.get()).getBlock() instanceof FantasyDoorBlock || ((BlockItem) itemRegistryObject.get()).getBlock() instanceof FantasySaplingBlock) {
                basicItem(itemRegistryObject.get());
                return;
            }
            if(((BlockItem) itemRegistryObject.get()).getBlock() instanceof FenceBlock) {
                String name = BuiltInRegistries.ITEM.getKey(itemRegistryObject.get()).getPath().substring(8);
                name = name.replace(name.substring(name.length() - 6), "");
                fenceInventory(BuiltInRegistries.ITEM.getKey(itemRegistryObject.get()).getPath(), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_planks", name)));
                return;
            }
            if(((BlockItem) itemRegistryObject.get()).getBlock() instanceof FantasyTrapDoorBlock block) {
                ResourceLocation resourceLocation = new ResourceLocation(BuiltInRegistries.ITEM.getKey(block.asItem()).getNamespace(),
                        "block/" + BuiltInRegistries.ITEM.getKey(block.asItem()).getPath() + "_bottom");
                getBuilder(block.asItem().toString()).parent(new ModelFile.UncheckedModelFile(resourceLocation));
                return;
            }
            if(((BlockItem) itemRegistryObject.get()).getBlock() instanceof FlowerBlock) {
                return;
            }
            blockItem(BuiltInRegistries.ITEM.getKey(itemRegistryObject.get()));
        });
    }

    public ItemModelBuilder blockItem(ResourceLocation item) {
        ResourceLocation resourceLocation = new ResourceLocation(item.getNamespace(),
                "block/" + item.getPath());
        return getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile(resourceLocation));
    }

}