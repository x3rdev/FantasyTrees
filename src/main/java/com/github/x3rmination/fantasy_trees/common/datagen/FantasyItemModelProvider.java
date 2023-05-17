package com.github.x3rmination.fantasy_trees.common.datagen;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.common.blocks.FantasyDoorBlock;
import com.github.x3rmination.fantasy_trees.common.blocks.FantasySaplingBlock;
import com.github.x3rmination.fantasy_trees.common.blocks.FantasyTrapDoorBlock;
import com.github.x3rmination.fantasy_trees.registry.BlockItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FantasyItemModelProvider extends ItemModelProvider {

    public FantasyItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, FantasyTrees.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        BlockItemRegistry.BLOCK_ITEMS.getEntries().forEach(itemRegistryObject -> {
            if(((BlockItem) itemRegistryObject.get()).getBlock() instanceof FantasyDoorBlock || ((BlockItem) itemRegistryObject.get()).getBlock() instanceof FantasySaplingBlock) {
                basicItem(itemRegistryObject.get());
                return;
            }
            if(((BlockItem) itemRegistryObject.get()).getBlock() instanceof FenceBlock) {
                String name = itemRegistryObject.get().getRegistryName().getPath().substring(8);
                name = name.replace(name.substring(name.length() - 6), "");
                fenceInventory(itemRegistryObject.get().getRegistryName().getPath(), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_planks", name)));
                return;
            }
            if(((BlockItem) itemRegistryObject.get()).getBlock() instanceof FantasyTrapDoorBlock block) {
                ResourceLocation resourceLocation = new ResourceLocation(block.asItem().getRegistryName().getNamespace(),
                        "block/" + block.asItem().getRegistryName().getPath() + "_bottom");
                getBuilder(block.asItem().toString()).parent(new ModelFile.UncheckedModelFile(resourceLocation));
                return;
            }
            blockItem(itemRegistryObject.get().getRegistryName());
        });
    }

    public ItemModelBuilder blockItem(ResourceLocation item) {
        ResourceLocation resourceLocation = new ResourceLocation(item.getNamespace(),
                "block/" + item.getPath());
        return getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile(resourceLocation));
    }

}
