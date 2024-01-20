package model;

import model.exception.DuplicateRecipeException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeList implements Writable {
    private String name;
    private List<Recipe> recipes;

    public RecipeList(String name) {
        this.name = name;
        this.recipes = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if recipe not already added, return true and add the given recipe to the recipe book,
    // else return false.
    public void addRecipe(Recipe r) throws DuplicateRecipeException {
        for (Recipe recipe : this.recipes) {
            if (r.getRecipeName().equalsIgnoreCase(recipe.getRecipeName())) {
                throw new DuplicateRecipeException(r);
            }
        }

        this.recipes.add(r);
    }

    // MODIFIES: this
    // EFFECTS: if recipe not already deleted, return true and remove a given recipe from the recipe book,
    // else return false.
    public boolean deleteRecipe(Recipe r) {
        for (Recipe recipe : this.recipes) {
            if (r.getRecipeName().equalsIgnoreCase(recipe.getRecipeName())) {
                this.recipes.remove(recipe);
                return true;
            }
        }

        return false;
    }

    public List<Recipe> getRecipes() {
        return this.recipes;
    }

    public String getName() {
        return this.name;
    }

    // EFFECTS: returns a list of names of all ingredients contained within all the recipes added to the recipe list
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> allIngredients = new ArrayList<>();
        for (Recipe nextRecipe : this.recipes) {
            allIngredients.addAll(nextRecipe.getRecipeIngredients());
        }

        return allIngredients;
    }

    public Recipe getRecipeAt(int index) {
        return this.recipes.get(index);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("recipes", recipesToJson());
        return json;
    }

    // EFFECTS: returns recipes in this recipe list as a JSON array
    private JSONArray recipesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipes) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
