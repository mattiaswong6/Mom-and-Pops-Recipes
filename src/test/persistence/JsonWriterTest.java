package persistence;

import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            RecipeList rl = new RecipeList("Mom and Pop's Recipes");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecipeList rl = new RecipeList("Mom and Pop's Recipes");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            rl = reader.read();
            assertEquals("Mom and Pop's Recipes", rl.getName());
            assertEquals(0, rl.getRecipes().size());
            assertEquals(0, rl.getAllIngredients().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            RecipeList rl = new RecipeList("Mom and Pop's Recipes");

            Recipe r1 = new Recipe("Chicken Wings", 10);
            Ingredient i1 = new Ingredient("salt");
            Ingredient i2 = new Ingredient("chicken");
            Ingredient i3 = new Ingredient("oil");
            r1.addIngredientToRecipe(i1);
            r1.addIngredientToRecipe(i2);
            r1.addIngredientToRecipe(i3);
            rl.addRecipe(r1);

            Recipe r2 = new Recipe("Steak", 20);
            Ingredient i4 = new Ingredient("beef");
            r2.addIngredientToRecipe(i4);
            r2.addIngredientToRecipe(i1);
            rl.addRecipe(r2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRecipeList.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRecipeList.json");
            rl = reader.read();
            assertEquals("Mom and Pop's Recipes", rl.getName());
            List<Recipe> recipes = rl.getRecipes();
            assertEquals(2, recipes.size());
            assertEquals("Chicken Wings", recipes.get(0).getRecipeName());
            assertEquals(10, recipes.get(0).getPrepTime());
            assertEquals("salt", recipes.get(0).getRecipeIngredients().get(0).getIngredientName());
            assertEquals("chicken", recipes.get(0).getRecipeIngredients().get(1).getIngredientName());
            assertEquals("oil", recipes.get(0).getRecipeIngredients().get(2).getIngredientName());

            assertEquals("Steak", recipes.get(1).getRecipeName());
            assertEquals(20, recipes.get(1).getPrepTime());
            assertEquals("beef", recipes.get(1).getRecipeIngredients().get(0).getIngredientName());
            assertEquals("salt", recipes.get(1).getRecipeIngredients().get(1).getIngredientName());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
