package persistence;

import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecipeList rl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRecipeList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRecipeList.json");
        try {
            RecipeList rl = reader.read();
            assertEquals("Mom and Pop's Recipes", rl.getName());
            List<Recipe> recipes = rl.getRecipes();
            List<Ingredient> ingredients = rl.getAllIngredients();
            assertEquals(0, recipes.size());
            assertEquals(0, ingredients.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRecipeList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRecipeList.json");
        try {
            RecipeList rl = reader.read();
            assertEquals("Mom and Pop's Recipes", rl.getName());
            List<Recipe> recipes = rl.getRecipes();
            List<Ingredient> ingredients = rl.getAllIngredients();
            assertEquals(2, recipes.size());
            assertEquals(5, ingredients.size());

            Recipe r1 = recipes.get(0);
            assertEquals(10, r1.getPrepTime());
            assertEquals(3, r1.getRecipeIngredients().size());
            assertEquals("salt", r1.getRecipeIngredients().get(0).getIngredientName());
            assertEquals("chicken", r1.getRecipeIngredients().get(1).getIngredientName());
            assertEquals("oil", r1.getRecipeIngredients().get(2).getIngredientName());

            Recipe r2 = recipes.get(1);
            assertEquals(20, r2.getPrepTime());
            assertEquals(2, r2.getRecipeIngredients().size());
            assertEquals("beef", r2.getRecipeIngredients().get(0).getIngredientName());
            assertEquals("salt", r2.getRecipeIngredients().get(1).getIngredientName());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
