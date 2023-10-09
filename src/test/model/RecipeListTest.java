package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeListTest {
    private Recipe testRecipe;
    private Ingredient i1;
    private Ingredient i2;
    private Ingredient i3;

    @BeforeEach
    void runBefore() {
        testRecipe = new Recipe("Fried Chicken", 30);
        i1 = new Ingredient("Chicken");
        i2 = new Ingredient("Oil");
        i3 = new Ingredient("Salt");
    }

    @Test
    void testConstructor() {
        assertEquals("Fried Chicken", testRecipe.getRecipeName());
        assertEquals(30, testRecipe.getPrepTime());
        List<Ingredient> ingredientsNeeded = testRecipe.getRecipeIngredients();
        assertEquals(0, ingredientsNeeded.size());
    }

    @Test
    void testAddOneIngredient() {
        assertTrue(testRecipe.addIngredientToRecipe(i1));
        List<Ingredient> ingredientsNeeded = testRecipe.getRecipeIngredients();
        assertEquals(1, ingredientsNeeded.size());
        assertEquals(i1, ingredientsNeeded.get(0));
    }

    @Test
    void testAddMultipleIngredients() {
        assertTrue(testRecipe.addIngredientToRecipe(i1));
        assertTrue(testRecipe.addIngredientToRecipe(i2));
        assertTrue(testRecipe.addIngredientToRecipe(i3));
        List<Ingredient> ingredientsNeeded = testRecipe.getRecipeIngredients();
        assertEquals(3, ingredientsNeeded.size());
        assertEquals(i1, ingredientsNeeded.get(0));
        assertEquals(i2, ingredientsNeeded.get(1));
        assertEquals(i3, ingredientsNeeded.get(2));
    }

    @Test
    void testAddDuplicateIngredients() {
        assertTrue(testRecipe.addIngredientToRecipe(i1));
        assertFalse(testRecipe.addIngredientToRecipe(i1));
        assertTrue(testRecipe.addIngredientToRecipe(i2));
        List<Ingredient> ingredientsNeeded = testRecipe.getRecipeIngredients();
        assertEquals(2, ingredientsNeeded.size());
        assertEquals(i1, ingredientsNeeded.get(0));
        assertEquals(i2, ingredientsNeeded.get(1));
    }

}
