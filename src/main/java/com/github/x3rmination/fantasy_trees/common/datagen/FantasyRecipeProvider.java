package com.github.x3rmination.fantasy_trees.common.datagen;

import com.github.x3rmination.fantasy_trees.common.blocks.FantasyLogBlock;
import com.github.x3rmination.fantasy_trees.registry.BlockItemRegistry;
import com.github.x3rmination.fantasy_trees.registry.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
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
    }
}
