package com.github.x3rmination.fantasy_trees.common.datagen;

import com.github.x3rmination.fantasy_trees.common.blocks.FantasyLogBlock;
import com.github.x3rmination.fantasy_trees.common.blocks.FantasyPlanksBlock;
import com.github.x3rmination.fantasy_trees.registry.BlockItemRegistry;
import com.github.x3rmination.fantasy_trees.registry.BlockRegistry;
import com.github.x3rmination.fantasy_trees.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class FantasyRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public FantasyRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {
        ItemLike[] fantasyLogs = BlockItemRegistry.BLOCK_ITEMS.getEntries().stream().map(RegistryObject::get).toArray(ItemLike[]::new);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(fantasyLogs),
                ItemRegistry.GLOWING_COAL.get(), 0.15F, 200)
                .unlockedBy("has_furnace", has(Blocks.FURNACE)).save(recipeConsumer);
        for (RegistryObject<Block> wood : BlockRegistry.WOODS.values()) {
            String name = wood.get().getRegistryName().getPath().substring(8);
            name = name.replace(name.substring(name.length() - 5), "");
            FantasyLogBlock log = (FantasyLogBlock) BlockRegistry.LOGS.get(name).get();
            ShapedRecipeBuilder.shaped(wood.get(), 3)
                    .define('l', log.asItem())
                    .pattern("   ")
                    .pattern("ll ")
                    .pattern("ll ")
                    .unlockedBy("has_" + name + "_log", has(log))
                    .save(recipeConsumer);
        }
        for (RegistryObject<Block> planks : BlockRegistry.PLANKS.values()) {
            String name = planks.get().getRegistryName().getPath().substring(8);
            name = name.replace(name.substring(name.length() - 7), "");
            FantasyLogBlock log = (FantasyLogBlock) BlockRegistry.LOGS.get(name).get();
            ShapelessRecipeBuilder.shapeless(planks.get(), 4)
                    .requires(log.asItem(), 1)
                    .unlockedBy("has_" + name + "_log", has(log))
                    .save(recipeConsumer);
        }
        for (RegistryObject<Block> stairs : BlockRegistry.STAIRS.values()) {
            String name = stairs.get().getRegistryName().getPath().substring(8);
            name = name.replace(name.substring(name.length() - 7), "");
            FantasyPlanksBlock planks = (FantasyPlanksBlock) BlockRegistry.PLANKS.get(name).get();
            ShapedRecipeBuilder.shaped(stairs.get(), 4)
                    .define('p', planks.asItem())
                    .pattern("p  ")
                    .pattern("pp ")
                    .pattern("ppp")
                    .unlockedBy("has_" + name + "_planks", has(planks))
                    .save(recipeConsumer);
        }
        for (RegistryObject<Block> door : BlockRegistry.DOORS.values()) {
            String name = door.get().getRegistryName().getPath().substring(8);
            name = name.replace(name.substring(name.length() - 5), "");
            FantasyPlanksBlock planks = (FantasyPlanksBlock) BlockRegistry.PLANKS.get(name).get();
            ShapedRecipeBuilder.shaped(door.get(), 3)
                    .define('p', planks.asItem())
                    .pattern("pp ")
                    .pattern("pp ")
                    .pattern("pp ")
                    .unlockedBy("has_" + name + "_planks", has(planks))
                    .save(recipeConsumer);
        }
        for (RegistryObject<Block> fence : BlockRegistry.FENCES.values()) {
            String name = fence.get().getRegistryName().getPath().substring(8);
            name = name.replace(name.substring(name.length() - 6), "");
            FantasyPlanksBlock planks = (FantasyPlanksBlock) BlockRegistry.PLANKS.get(name).get();
            ShapedRecipeBuilder.shaped(fence.get(), 3)
                    .define('p', planks.asItem())
                    .define('s', Items.STICK)
                    .pattern("   ")
                    .pattern("psp")
                    .pattern("psp")
                    .unlockedBy("has_" + name + "_planks", has(planks))
                    .save(recipeConsumer);
        }
        for (RegistryObject<Block> fence_gate : BlockRegistry.FENCE_GATES.values()) {
            String name = fence_gate.get().getRegistryName().getPath().substring(8);
            name = name.replace(name.substring(name.length() - 11), "");
            FantasyPlanksBlock planks = (FantasyPlanksBlock) BlockRegistry.PLANKS.get(name).get();
            ShapedRecipeBuilder.shaped(fence_gate.get(), 1)
                    .define('p', planks.asItem())
                    .define('s', Items.STICK)
                    .pattern("   ")
                    .pattern("sps")
                    .pattern("sps")
                    .unlockedBy("has_" + name + "_planks", has(planks))
                    .save(recipeConsumer);
        }
        for (RegistryObject<Block> crafting_table : BlockRegistry.CRAFTING_TABLES.values()) {
            String name = crafting_table.get().getRegistryName().getPath().substring(8);
            name = name.replace(name.substring(name.length() - 15), "");
            FantasyPlanksBlock planks = (FantasyPlanksBlock) BlockRegistry.PLANKS.get(name).get();
            ShapedRecipeBuilder.shaped(crafting_table.get(), 1)
                    .define('p', planks.asItem())
                    .pattern("   ")
                    .pattern("pp ")
                    .pattern("pp ")
                    .unlockedBy("has_" + name + "_planks", has(planks))
                    .save(recipeConsumer);
        }
    }
}
