package persistence;

import model.Ingredient;
import model.Recipe;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRecipe(String name, int prepTime, List<Ingredient> ingredients, Recipe recipe) {
        assertEquals(name, recipe.getRecipeName());
        assertEquals(prepTime, recipe.getPrepTime());
        assertEquals(ingredients, recipe.getRecipeIngredients());
    }
}
