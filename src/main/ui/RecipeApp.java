package ui;

import model.Ingredient;
import model.Recipe;
import model.RecipeList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// recipe manager application
public class RecipeApp {
    private static final String JSON_STORE = "./data/workroom.json";
    private RecipeList recipeList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the recipe app
    public RecipeApp() throws IOException {
        runRecipeApp();
    }

    // MODIFIES: this
    // EFFECTS: process user input
    private void runRecipeApp() throws IOException {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayGeneralMenu();
            command = input.next();

            if (command.equals("7")) {
                showQuitReminder();
                command = input.next();
                keepGoing = !processQuittingCommand(command);

            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");

    }

    private boolean processQuittingCommand(String command) {
        boolean doQuit = false;
        boolean keepGoing = true;
        while (keepGoing) {
            if (command.equals("1")) {
                saveRecipeList();
                keepGoing = false;
                doQuit = true;
            } else if (command.equals("2")) {
                System.out.println("\nRecipe list not saved.");
                keepGoing = false;
                doQuit = true;
            } else if (command.equals("3")) {
                doQuit = false;
                keepGoing = false;
            } else {
                System.out.println("Selection not valid...");
                keepGoing = false;
                doQuit = false;
            }
        }
        return doQuit;
    }

    // EFFECTS: gives the user the option to save recipe list to file before quitting
    private void showQuitReminder() {
        System.out.println("\nWould you like to save your recipe list before quitting?");
        System.out.println("\t1 -> Yes, save this recipe list and quit");
        System.out.println("\t2 -> No, don't save and quit");
        System.out.println("\t3 -> Go back to main menu");
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
        } else if (command.equals("5")) {
            saveRecipeList();
        } else if (command.equals("6")) {
            loadRecipeList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: shows the list of recipes currently added to the recipe manager
    private void doShowRecipes() {
        if (recipeList.getRecipes().isEmpty()) {
            System.out.println("No recipes currently added.");
        } else {
            System.out.println("\nThe recipe book has the following recipes added:");
        }

        for (Recipe recipe : recipeList.getRecipes()) {
            System.out.println("\t- " + recipe.getRecipeName() + " (" + recipe.getPrepTime() + " minutes)");
        }
    }

    // EFFECTS: show a singular recipe's title, prep time, and list of ingredients contained in the recipe
    private void doShowRecipe() {
        if (recipeList.getRecipes().isEmpty()) {
            System.out.println("No recipes currently added.");
        } else {
            System.out.println("\nWhich recipe would you like to see from the following list?");
            for (Recipe recipe : recipeList.getRecipes()) {
                System.out.println("\t- " + recipe.getRecipeName() + "(" + recipe.getPrepTime() + " minutes)");
            }

            String name = input.next();

            boolean foundRecipe = false;

            for (Recipe recipe : recipeList.getRecipes()) {
                if (name.equalsIgnoreCase(recipe.getRecipeName())) {
                    foundRecipe = true;
                    System.out.println("\n~~~ Recipe for " + recipe.getRecipeName() + " (" + recipe.getPrepTime()
                            + " minutes) ~~~");
                    for (Ingredient ingredient : recipe.getRecipeIngredients()) {
                        System.out.println("\t- " + ingredient.getIngredientName());
                    }
                }
            }

            if (!foundRecipe) {
                System.out.println("Sorry, there are no recipes of that name in this recipe book.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a recipe to the recipe book
    private void doAddRecipe() {
        System.out.println("What is the name of the recipe?");
        String name = input.next();
        System.out.println("How many minutes does this recipe take to make?");
        String timeString = input.next();
        int time = Integer.parseInt(timeString);
        Recipe r = new Recipe(name, time);

        if (recipeList.addRecipe(r)) {
            System.out.println("New recipe " + name + " (" + time + " mins) created.");
            keepAddingIngredients(r);

            System.out.println("Recipe for " + name + " finished and added to recipe book!");
        } else {
            System.out.println("Sorry, recipe for " + name + " already exists.");
        }
    }

    // MODIFIES: r
    // EFFECTS: adds an ingredient to a given recipe and allows user to keep adding ingredients or finish recipe
    private void keepAddingIngredients(Recipe r) {
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

            keepAdding = checkValidInput();
        }
    }

    // EFFECTS: keeps producing the add ingredients menu until a valid selection is inputted, produces true if
    // user selects to add another ingredient and false if user wants to finish recipe.
    private boolean checkValidInput() {
        int valid = 0;
        boolean keepAdding = true;
        while (valid == 0) {
            displayAddingMenu();
            String command = input.next();
            if (command.equals("2")) {
                keepAdding = false;
                valid = 1;
            } else if (command.equals("1")) {
                valid = 1;
            } else {
                System.out.println("Selection not valid....");
            }
        }

        return keepAdding;
    }


    // EFFECTS: displays menu of options for user when building a recipe
    private void displayAddingMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add Another Ingredient");
        System.out.println("\t2 -> Finish Recipe");
    }

    // MODIFIES: this
    // EFFECTS: deletes a recipe from the recipe book
    private void doDeleteRecipe() {
        System.out.println("\nPlease type the name of the recipe you would like to delete from the following list:");
        printRecipesInBook();
        String name = input.next();
        Recipe tempRecipe = new Recipe(name, 1);

        if (recipeList.deleteRecipe(tempRecipe)) {
            System.out.println("Recipe for " + name + " successfully deleted from recipe book.");
        } else {
            System.out.println("Sorry, recipe for " + name + " does not exist.");
        }
    }

    // EFFECTS: saves the recipe list to file
    private void saveRecipeList() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipeList);
            jsonWriter.close();
            System.out.println("Saved " + recipeList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads recipeList from file
    private void loadRecipeList() {
        try {
            recipeList = jsonReader.read();
            System.out.println("Loaded " + recipeList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints the list of recipes in the recipe list, or prints no recipes added if the list is empty.
    private void printRecipesInBook() {
        if (recipeList.getRecipes().isEmpty()) {
            System.out.println("No recipes currently added.");
        } else {
            for (Recipe recipe : recipeList.getRecipes()) {
                System.out.println("\t- " + recipe.getRecipeName() + "(" + recipe.getPrepTime() + " minutes)");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes recipe list
    private void init() {
        recipeList = new RecipeList("Mom and Pop's Recipes");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays menu of options to user
    private void displayGeneralMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> View All Recipes");
        System.out.println("\t2 -> View Specific Recipe");
        System.out.println("\t3 -> Add Recipe");
        System.out.println("\t4 -> Delete Recipe");
        System.out.println("\t5 -> Save Recipe List to File");
        System.out.println("\t6 -> Load Recipe List from File");
        System.out.println("\t7 -> Quit");
    }


}
