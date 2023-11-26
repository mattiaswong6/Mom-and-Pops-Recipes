package ui;

import model.Event;
import model.EventLog;
import model.RecipeList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.*;


import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RecipeAppUI extends JFrame implements WindowListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static final int HOME_TAB_INDEX = 0;
    public static final int RECIPES_TAB_INDEX = 1;

    private static final String JSON_STORE = "./data/recipeList.json";
    private RecipeList recipeList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTabbedPane topbar;
    private JPanel recipeTab;
    private JPanel homeTab;



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
        recipeTab = new RecipesTab(this);
        homeTab = new HomeTab(this);

        addWindowListener(this);

        loadTabs();
        add(topbar);

        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: adds home tab and recipes tab to this UI
    private void loadTabs() {
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
        } catch (FileNotFoundException e) {
            returnMessage = "Unable to save!";
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
        ((RecipesTab)this.recipeTab).loadRecipesIntoScroll();

        return returnMessage;
    }

    public RecipeList getRecipeList() {
        return this.recipeList;
    }

    public void printLog(EventLog el) {
        String printed = "";
        for (Event next : el) {
            printed = printed + next.toString() + "\n\n";
        }
        System.out.println(printed);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
