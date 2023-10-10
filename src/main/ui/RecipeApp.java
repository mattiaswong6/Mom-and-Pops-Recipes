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
        } else if (command.equals("3")) {
            doAddRecipe();
        } else if (command.equals("4")) {
            doDeleteRecipe();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: produces the list of recipes currently added to the recipe manager
    private void doShowRecipes() {
        if (recipeList.getRecipes().isEmpty()) {
            System.out.println("No recipes currently added.");
        } else {
            System.out.println("\nThe recipe book has the following recipes added:");
        }

        for (Recipe recipe : recipeList.getRecipes()) {
            System.out.println("\t‣" + recipe.getRecipeName() + " (" + recipe.getPrepTime() + " minutes)");
        }
    }

    // EFFECTS: produces a singular recipe with a list of ingredients contained in the recipe
    private void doShowRecipe() {
        if (recipeList.getRecipes().isEmpty()) {
            System.out.println("No recipes currently added.");
        } else {
            System.out.println("\nWhich recipe would you like to see from the following list?");
            for (Recipe recipe : recipeList.getRecipes()) {
                System.out.println("\t‣" + recipe.getRecipeName() + "(" + recipe.getPrepTime() + " minutes)");
            }

            String name = input.next();

            boolean foundRecipe = false;

            for (Recipe recipe : recipeList.getRecipes()) {
                if (name.equalsIgnoreCase(recipe.getRecipeName())) {
                    foundRecipe = true;
                    System.out.println("\n~~~ Recipe for " + recipe.getRecipeName() + " (" + recipe.getPrepTime()
                            + " minutes) ~~~");
                    for (Ingredient ingredient : recipe.getRecipeIngredients()) {
                        System.out.println("\t‣" + ingredient.getIngredientName());
                    }
                }
            }

            if (!foundRecipe) {
                System.out.println("Sorry, there are no recipes of that name in this recipe book.");
            }
        }
    }

    @SuppressWarnings("methodlength")
    // MODIFIES: this
    // EFFECTS: adds a recipe to the recipe book
    private void doAddRecipe() {
        System.out.println("What is the name of the recipe?");
        String name = input.next();
        System.out.println("How long does this recipe take?");
        String timeString = input.next();
        int time = Integer.parseInt(timeString);
        Recipe r = new Recipe(name, time);

        if (recipeList.addRecipe(r)) {
            System.out.println("New recipe " + name + " (" + time + " mins) created.");

            boolean keepAdding = true;

            while (keepAdding) {
                System.out.println("Add an ingredient below.");
                String ingredientName = input.next();
                Ingredient i = new Ingredient(ingredientName);
                if (r.addIngredientToRecipe(i)) {
                    System.out.println(i.getIngredientName() + " added to recipe.");
                } else {
                    System.out.println("Ingredient already added to recipe.");
                }
                displayAddingMenu();
                String command = input.next();
                if (command.equals("2")) {
                    keepAdding = false;
                }
            }
            System.out.println("Recipe for " + name + " finished and added to recipe book!");
        } else {
            System.out.println("Sorry, recipe for " + name + " already exists.");
        }
    }

    private void displayAddingMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add Another Ingredient");
        System.out.println("\t2 -> Finish Recipe");
    }

    // MODIFIES: this
    // EFFECTS: deletes a recipe from the recipe book
    private void doDeleteRecipe() {
        System.out.println("\nPlease type the name of the recipe you would like to delete from the following list:");
        for (Recipe recipe : recipeList.getRecipes()) {
            System.out.println("\t‣" + recipe.getRecipeName() + "(" + recipe.getPrepTime() + " minutes)");
        }
        String name = input.next();
        Recipe tempRecipe = new Recipe(name, 1);

        if (recipeList.deleteRecipe(tempRecipe)) {
            System.out.println("Recipe for " + name + " successfully deleted from recipe book.");
        } else {
            System.out.println("Sorry, recipe for " + name + " does not exist.");
        }
    }

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
