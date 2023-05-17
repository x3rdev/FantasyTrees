package com.github.x3rmination.fantasy_trees.common.datagen;

import com.github.x3rmination.fantasy_trees.FantasyTrees;
import com.github.x3rmination.fantasy_trees.common.blocks.FantasyDoorBlock;
import com.github.x3rmination.fantasy_trees.common.blocks.FantasyLogBlock;
import com.github.x3rmination.fantasy_trees.common.blocks.FantasySaplingBlock;
import com.github.x3rmination.fantasy_trees.common.blocks.FantasyTrapDoorBlock;
import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FantasyBlockStateProvider extends BlockStateProvider {

    public FantasyBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, FantasyTrees.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        BlockRegistry.WOODS.forEach((name, blockRegistryObject) -> woodBlock((FantasyLogBlock) blockRegistryObject.get()));
        BlockRegistry.LOGS.forEach((name, blockRegistryObject) -> logBlock((FantasyLogBlock) blockRegistryObject.get()));
        BlockRegistry.STRIPPED_WOODS.forEach((name, blockRegistryObject) -> woodBlock((FantasyLogBlock) blockRegistryObject.get()));
        BlockRegistry.STRIPPED_LOGS.forEach((name, blockRegistryObject) -> logBlock((FantasyLogBlock) blockRegistryObject.get()));
        BlockRegistry.LEAVES.forEach((name, blockRegistryObject) -> leavesBlock(((LeavesBlock) blockRegistryObject.get()), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_leaves", name))));
        BlockRegistry.PLANKS.forEach((name, blockRegistryObject) -> simpleBlock(blockRegistryObject.get()));
        BlockRegistry.STAIRS.forEach((name, blockRegistryObject) -> stairsBlock(((StairBlock) blockRegistryObject.get()), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_planks", name))));
        BlockRegistry.DOORS.forEach((name, blockRegistryObject) -> doorBlock(((FantasyDoorBlock) blockRegistryObject.get()), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_door_bottom", name)), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_door_top", name))));
        BlockRegistry.TRAPDOORS.forEach((name, blockRegistryObject) -> trapdoorBlock((FantasyTrapDoorBlock) blockRegistryObject.get(), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_planks", name)), true));
        BlockRegistry.FENCES.forEach((name, blockRegistryObject) -> fenceBlock(((FenceBlock) blockRegistryObject.get()), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_planks", name))));
        BlockRegistry.FENCE_GATES.forEach((name, blockRegistryObject) -> fenceGateBlock(((FenceGateBlock) blockRegistryObject.get()), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_planks", name))));
        BlockRegistry.CRAFTING_TABLES.forEach((name, blockRegistryObject) -> craftingTableBlock(((CraftingTableBlock) blockRegistryObject.get()), new ResourceLocation(FantasyTrees.MOD_ID, String.format("block/fantasy_%s_crafting_table", name))));
        BlockRegistry.SAPLINGS.forEach((name, blockRegistryObject) -> saplingBlock(((FantasySaplingBlock) blockRegistryObject.get()), new ResourceLocation(FantasyTrees.MOD_ID, String.format("item/fantasy_%s_sapling", name))));
    }

    public void woodBlock(RotatedPillarBlock block) {
        ResourceLocation blockTexture = new ResourceLocation(blockTexture(block).getNamespace(), blockTexture(block).getPath().replace("wood", "log"));
        woodBlock(block, models().cubeColumn(blockName(block), blockTexture, blockTexture));
    }
    public void woodBlock(RotatedPillarBlock block, ModelFile modelFile) {
        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(modelFile).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(modelFile).rotationX(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(modelFile).rotationX(90).rotationY(90).addModel();
    }

    public void leavesBlock(LeavesBlock block, ResourceLocation resourceLocation) {
        getVariantBuilder(block)
                .partialState().modelForState().modelFile(models()
                        .singleTexture(blockName(block),
                        new ResourceLocation("block/leaves"),
                        "all",
                        resourceLocation
                )).addModel();
    }

    public void craftingTableBlock(CraftingTableBlock block, ResourceLocation resourceLocation) {
        getVariantBuilder(block)
                .partialState().modelForState().modelFile(models().cube(blockName(block),
                        new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath() + "_bottom"),
                        new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath() + "_top"),
                        new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath() + "_side"),
                        new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath() + "_side"),
                        new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath() + "_side"),
                        new ResourceLocation(resourceLocation.getNamespace(), resourceLocation.getPath() + "_side")
                )).addModel();
    }

    public void saplingBlock(SaplingBlock block, ResourceLocation resourceLocation) {
        getVariantBuilder(block).partialState().modelForState().modelFile(models().cross(blockName(block), resourceLocation)).addModel();
    }
    private String blockName(Block block) {
        return block.getRegistryName().getPath();
    }
}
