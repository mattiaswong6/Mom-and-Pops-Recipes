package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe implements Writable {
    private final String name;
    private List<Ingredient> recipeIngredients;
    private int prepTime;

    // REQUIRES: recipeName has a non-zero length, time > 0.
    // EFFECTS: creates a new Recipe with a given name and prep time (in minutes),
    // and an empty list of ingredients required.
    public Recipe(String recipeName, int time) {
        this.name = recipeName;
        this.prepTime = time;
        this.recipeIngredients = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("New recipe for " + name + " created"));
    }

    // MODIFIES: this
    // EFFECTS: return true if ingredient has not already been added to recipe and add ingredient to recipe,
    // or return false.
    public boolean addIngredientToRecipe(Ingredient i) {
        for (Ingredient ingredient : this.recipeIngredients) {
            if (i.getIngredientName().equalsIgnoreCase(ingredient.getIngredientName())) {
                return false;
            }
        }

        this.recipeIngredients.add(i);
        EventLog.getInstance().logEvent(new Event(i.getIngredientName() + " added to " + this.name
                + " recipe"));
        return true;

    }

    public String getRecipeName() {
        return this.name;
    }

    public int getPrepTime() {
        return this.prepTime;
    }

    public List<Ingredient> getRecipeIngredients() {
        return this.recipeIngredients;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("prepTime", prepTime);
        json.put("ingredients", ingredientsToJson());
        return json;
    }

    // EFFECTS: returns ingredients in this recipe as a JSON array
    private JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredient i : recipeIngredients) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // REQUIRES: time > 0
    // EFFECTS: changes the prep time of the recipe to a new given time (in minutes)
    public void changePrepTime(int time) {
        this.prepTime = time;
        EventLog.getInstance().logEvent(new Event("Prep time for " + this.name + " set to "
                + Integer.toString(time) + " minutes"));
    }

}
