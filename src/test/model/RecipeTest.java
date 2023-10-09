package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    private RecipeList testRecipeList;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;

    @BeforeEach
    void runBefore() {
        testRecipeList = new RecipeList();
        r1 = new Recipe("Fried Chicken", 30);
        r2 = new Recipe("BBQ Ribs", 20);
        r3 = new Recipe("Steak and Fries", 15);
    }

    @Test
    void testConstructor() {
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(0, recipes.size());
    }

    @Test
    void testAddRecipe() {
        assertTrue(testRecipeList.addRecipe(r1));
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(1, recipes.size());
        assertEquals(r1, recipes.get(0));
    }

    @Test
    void testAddMultipleRecipes() {
        assertTrue(testRecipeList.addRecipe(r1));
        assertTrue(testRecipeList.addRecipe(r2));
        assertTrue(testRecipeList.addRecipe(r3));
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(3, recipes.size());
        assertEquals(r1, recipes.get(0));
        assertEquals(r2, recipes.get(1));
        assertEquals(r3, recipes.get(2));
    }

    @Test
    void testAddDuplicateRecipes() {
        assertTrue(testRecipeList.addRecipe(r1));
        assertFalse(testRecipeList.addRecipe(r1));
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(1, recipes.size());
        assertEquals(r1, recipes.get(0));
    }

    @Test
    void testRemoveRecipe() {
        assertTrue(testRecipeList.addRecipe(r1));
        assertTrue(testRecipeList.addRecipe(r2));
        assertTrue(testRecipeList.addRecipe(r3));
        assertTrue(testRecipeList.deleteRecipe(r1));
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(2, recipes.size());
        assertEquals(r2, recipes.get(0));
        assertEquals(r3, recipes.get(1));
    }

    @Test
    void testRemoveNonExistRecipe() {
        assertTrue(testRecipeList.addRecipe(r1));
        assertTrue(testRecipeList.addRecipe(r2));
        assertFalse(testRecipeList.deleteRecipe(r3));
        List<Recipe> recipes = testRecipeList.getRecipes();
        assertEquals(2, recipes.size());
        assertEquals(r1, recipes.get(0));
        assertEquals(r2, recipes.get(1));
    }

}