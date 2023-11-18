package model;

import model.exception.DuplicateRecipeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeListTest {
    private RecipeList testRecipeList;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Ingredient i1;
    private Ingredient i2;
    private Ingredient i3;
    private Ingredient i4;

    @BeforeEach
    void runBefore() {
        testRecipeList = new RecipeList("Mom's Recipes");
        r1 = new Recipe("Fried Chicken", 30);
        r2 = new Recipe("BBQ Ribs", 20);
        r3 = new Recipe("Steak and Fries", 15);
        i1 = new Ingredient("Chicken");
        i2 = new Ingredient("Oil");
        i3 = new Ingredient("Salt");
        i4 = new Ingredient("Pepper");
    }

    @Test
    void testConstructor() {
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(0, recipes.size());
        assertEquals("Mom's Recipes", testRecipeList.getName());
    }

    @Test
    void testAddRecipe() {
        try {
            testRecipeList.addRecipe(r1);
        } catch (DuplicateRecipeException e) {
            fail();
        }

        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(1, recipes.size());
        assertEquals(r1, recipes.get(0));
    }

    @Test
    void testAddMultipleRecipes() {
        try {
            testRecipeList.addRecipe(r1);
            testRecipeList.addRecipe(r2);
            testRecipeList.addRecipe(r3);
        } catch (DuplicateRecipeException e) {
            fail();
        }
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(3, recipes.size());
        assertEquals(r1, recipes.get(0));
        assertEquals(r2, recipes.get(1));
        assertEquals(r3, recipes.get(2));
    }

    @Test
    void testAddDuplicateRecipes() {
        try {
            testRecipeList.addRecipe(r1);
        } catch (DuplicateRecipeException e) {
            fail();
        }
        try {
            testRecipeList.addRecipe(r1);
            fail();
        } catch (DuplicateRecipeException e) {
            // expected
        }
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(1, recipes.size());
        assertEquals(r1, recipes.get(0));
    }

    @Test
    void testRemoveRecipe() {
        try {
            testRecipeList.addRecipe(r1);
            testRecipeList.addRecipe(r2);
            testRecipeList.addRecipe(r3);
        } catch (DuplicateRecipeException e) {
            fail();
        }
        assertTrue(testRecipeList.deleteRecipe(r1));
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(2, recipes.size());
        assertEquals(r2, recipes.get(0));
        assertEquals(r3, recipes.get(1));
    }

    @Test
    void testRemoveNonExistRecipe() {
        try {
            testRecipeList.addRecipe(r1);
            testRecipeList.addRecipe(r2);
        } catch (DuplicateRecipeException e) {
            fail();
        }
        assertFalse(testRecipeList.deleteRecipe(r3));
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(2, recipes.size());
        assertEquals(r1, recipes.get(0));
        assertEquals(r2, recipes.get(1));
    }

    @Test
    void testAllIngredientsNone() {
        List<Ingredient> allIngredients = testRecipeList.getAllIngredients();
        assertEquals(0, allIngredients.size());
    }

    @Test
    void testAllIngredientsMany() {
        assertTrue(r1.addIngredientToRecipe(i1));
        assertTrue(r2.addIngredientToRecipe(i2));
        assertTrue(r3.addIngredientToRecipe(i3));
        assertTrue(r3.addIngredientToRecipe(i4));

        try {
            testRecipeList.addRecipe(r1);
            testRecipeList.addRecipe(r2);
            testRecipeList.addRecipe(r3);
        } catch (DuplicateRecipeException e) {
            fail();
        }

        List<Ingredient> allIngredients = testRecipeList.getAllIngredients();
        assertEquals(4, allIngredients.size());
    }

}