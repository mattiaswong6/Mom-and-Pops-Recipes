package ui;

import model.Ingredient;
import model.Recipe;
import model.RecipeList;

import java.util.*;

// recipe manager application
public class RecipeApp {
    private RecipeList recipeList;
    private Scanner input;


    // EFFECTS: runs the recipe app
    public RecipeApp() {
        runRecipeApp();
    }

    // MODIFIES: this
    // EFFECTS: process user input
    private void runRecipeApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayGeneralMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("5")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            doShowRecipes();
        } else if (command.equals("2")) {
            doShowRecipe();
//        } else if (command.equals("3")) {
//            doAddRecipe();
//        } else if (command.equals("4")) {
//            doTransfer();
//        } else {
//            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: produces the list of recipes currently added to the recipe manager
    private void doShowRecipes() {
        if (recipeList.getRecipes().isEmpty()) {
            System.out.println("No recipes currently added.");
        }

        for (Recipe recipe : recipeList.getRecipes()) {
            System.out.println(recipe.getRecipeName());
        }
    }

    // EFFECTS: produces a singular recipe with a list of ingredients contained in the recipe
    private void doShowRecipe() {
        if (recipeList.getRecipes().isEmpty()) {
            System.out.println("No recipes currently added.");
        } else {
            System.out.println("Which recipe would you like to see?");
            for (Recipe recipe : recipeList.getRecipes()) {
                System.out.println(recipe.getRecipeName());
            }

            String name = input.next();

            for (Recipe recipe : recipeList.getRecipes()) {
                if (name.equalsIgnoreCase(recipe.getRecipeName())) {
                    for (Ingredient ingredient : recipe.getRecipeIngredients()) {
                        System.out.println(ingredient.getIngredientName());
                    }
                }
            }

            System.out.println("Sorry, there are no recipes of that name in this recipe book.");
        }
    }

    // EFFECTS: adds a recipe to the recipe book
//    private void doAddRecipe() {
//        System.out.println("What is the name of the recipe?");
//        String name = input.next();
//        System.out.println("New recipe for " + name + " created. How long does this recipe take to make?");
//        String time = input.next();
//
//        recipeList.addRecipe();
//
//        System.out.println(name + " takes " + time + " minutes to make. Please add your first ingredient below.");
//        r = new Recipe(name, time);
//        boolean keepAdding = true;
//
//
//    }

    // MODIFIES: this
    // EFFECTS: initializes recipe list
    private void init() {
        recipeList = new RecipeList();
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayGeneralMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> View All Recipes");
        System.out.println("\t2 -> View Specific Recipe");
        System.out.println("\t3 -> Add Recipe");
        System.out.println("\t4 -> Delete Recipe");
        System.out.println("\t5 -> Quit");
    }


}
