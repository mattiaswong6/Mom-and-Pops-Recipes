package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeList {
    private List<Recipe> recipes;

    public RecipeList() {
        this.recipes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if recipe not already added, return true and add the given recipe to the recipe book,
    // else return false.
    public boolean addRecipe(Recipe r) {
        for (Recipe recipe : this.recipes) {
            if (Objects.equals(recipe.getRecipeName(), r.getRecipeName())) {
                return false;
            }
        }

        this.recipes.add(r);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: if recipe not already deleted, return true and remove a given recipe from the recipe book,
    // else return false.
    public boolean deleteRecipe(Recipe r) {
        for (Recipe recipe : this.recipes) {
            if (Objects.equals(recipe.getRecipeName(), r.getRecipeName())) {
                this.recipes.remove(recipe);
                return true;
            }
        }

        return false;
    }

    public List<Recipe> getRecipes() {
        return this.recipes;
    }

}
