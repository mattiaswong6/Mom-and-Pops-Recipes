package ui;

import model.Recipe;
import model.RecipeList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.*;


import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RecipeAppUI extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    public static final int HOME_TAB_INDEX = 0;
    public static final int RECIPES_TAB_INDEX = 1;

    private static final String JSON_STORE = "./data/recipeList.json";
    private RecipeList recipeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTabbedPane topbar;



    // MODIFIES: this
    // EFFECTS: creates RecipeList, displays topbar and tabs
    public RecipeAppUI() {
        super("RecipeApp Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        recipeList = new RecipeList("Mom and Pops Recipes");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        topbar = new JTabbedPane();
        topbar.setTabPlacement(JTabbedPane.TOP);

        loadTabs();
        add(topbar);

        setVisible(true);
    }

//    public JPanel getRecipeTab() {
//        return this.recipeTab;
//    }

    // MODIFIES: this
    // EFFECTS: adds home tab and recipes tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel recipeTab = new RecipesTab(this);

        topbar.add(homeTab, HOME_TAB_INDEX);
        topbar.setTitleAt(HOME_TAB_INDEX, "Home");
        topbar.add(recipeTab, RECIPES_TAB_INDEX);
        topbar.setTitleAt(RECIPES_TAB_INDEX, "Recipes");

    }

    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return topbar;
    }

    // EFFECTS: saves the recipe list to file
    public String saveRecipeList() {
        String returnMessage = null;
        try {
            jsonWriter.open();
            jsonWriter.write(recipeList);
            jsonWriter.close();
            returnMessage = "Recipe List successfully saved!";
//            System.out.println("Saved " + recipeList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            returnMessage = "Unable to save!";
//            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        return returnMessage;
    }

    // MODIFIES: this
    // EFFECTS: loads recipeList from file
    public String loadRecipeList() {
        String returnMessage = null;
        try {
            recipeList = jsonReader.read();
            returnMessage = "Loaded " + recipeList.getName() + " from " + JSON_STORE;
        } catch (IOException e) {
            returnMessage = "Unable to read from file: " + JSON_STORE;
        }
        return returnMessage;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public RecipeList getRecipeList() {
        return this.recipeList;
    }



}
