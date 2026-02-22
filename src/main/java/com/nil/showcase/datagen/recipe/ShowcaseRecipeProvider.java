package com.nil.showcase.datagen.recipe;

import java.util.function.Consumer;

import com.nil.showcase.items.ShowcaseItems;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

public class ShowcaseRecipeProvider extends RecipeProvider {

    public ShowcaseRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ShowcaseItems.COMPRESSED_DIRT.get())
            .requires(Items.DIRT, 9)
            .unlockedBy("has_dirt", has(Items.DIRT))
            .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ShowcaseItems.DIRT_STICK.get())
            .define('d', ShowcaseItems.COMPRESSED_DIRT.get())
            .pattern("d")
            .pattern("d")
            .unlockedBy("has_compressed_dirt", has(ShowcaseItems.COMPRESSED_DIRT.get()))
            .save(consumer);
    }
}
