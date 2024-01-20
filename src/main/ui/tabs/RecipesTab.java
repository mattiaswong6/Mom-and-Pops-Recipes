package ui.tabs;

import model.Ingredient;
import model.Recipe;
import model.exception.DuplicateRecipeException;
import ui.ButtonNames;
import ui.RecipeAppUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecipesTab extends Tab implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;
    private JButton addRecipeButton;
    private JButton deleteRecipeButton;
    private JButton viewRecipeButton;

    //EFFECTS: constructs a recipes tab for console with scroll pane of recipes and Add, Delete, View Recipe buttons
    public RecipesTab(RecipeAppUI controller) {
        super(controller);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(-1);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        this.add(listScrollPane, BorderLayout.CENTER);

        addRecipeButton = new JButton(new RecipesTab.AddRecipeAction());
        deleteRecipeButton = new JButton(new RecipesTab.DeleteRecipeAction());
        viewRecipeButton = new JButton(new RecipesTab.ViewRecipeAction());
        deleteRecipeButton.setEnabled(false);
        viewRecipeButton.setEnabled(false);

        addButtonPanel();

    }

    //MODIFIES: this
    //EFFECTS: adds the Add, Delete, View Recipes buttons to the panel.
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(addRecipeButton);
        buttonPanel.add(deleteRecipeButton);
        buttonPanel.add(viewRecipeButton);

        this.add(buttonPanel, BorderLayout.PAGE_END);
    }

    //EFFECTS: loads all recipes in the current recipe list into the scroll panel
    public void loadRecipesIntoScroll() {
        listModel.removeAllElements();
        for (Recipe r : getController().getRecipeList().getRecipes()) {
            listModel.addElement(r.getRecipeName());
        }
    }

    //EFFECTS: disables the delete and view recipe buttons when a recipe is not selected
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable delete and view recipe buttons.
                deleteRecipeButton.setEnabled(false);
                viewRecipeButton.setEnabled(false);

            } else {
                //Selection, enable the delete and view recipe buttons.
                deleteRecipeButton.setEnabled(true);
                viewRecipeButton.setEnabled(true);
            }
        }
    }

    //EFFECTS: shows user a pop-up window containing a recipes ingredients and prep time
    private class ViewRecipeAction extends AbstractAction implements ActionListener {
        ViewRecipeAction() {
            super(ButtonNames.VIEW.getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.VIEW.getValue())) {
                int index = list.getSelectedIndex();
                Recipe selectedRecipe = getController().getRecipeList().getRecipeAt(index);

                Object[] options = {"Ok, go back",
                        "Delete Recipe"};
                int reply = JOptionPane.showOptionDialog(null,
                        listIngredients(selectedRecipe), selectedRecipe.getRecipeName(), JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (reply == JOptionPane.NO_OPTION) {
                    getController().getRecipeList().deleteRecipe(selectedRecipe);
                    listModel.remove(index);
                    JOptionPane.showMessageDialog(null,
                            "Recipe for " + selectedRecipe.getRecipeName() + " deleted!",
                            "Delete Message", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
    }

    //EFFECTS: produces a string containing a given recipe's name, prep time, and list of ingredients
    private String listIngredients(Recipe r) {
        String enter = "\n";
        String tab = "\t";
        String bigTab = "\t\t\t\t\t\t";
        String returnedIngredients = "~~~ Recipe for " + r.getRecipeName() + " ~~~" + enter + enter + tab
                + "Prep time: " + r.getPrepTime() + " minutes" + enter + enter + tab + "Ingredients:" + enter;

        for (Ingredient i : r.getRecipeIngredients()) {
            returnedIngredients = returnedIngredients + bigTab + "- " + i.getIngredientName() + enter;
        }
        return returnedIngredients;
    }

    //EFFECTS: allows user to confirm their deletion then deletes a selected recipe from the recipe list
    private class DeleteRecipeAction extends AbstractAction {
        DeleteRecipeAction() {
            super(ButtonNames.DELETE.getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.DELETE.getValue())) {
                int reply = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete this recipe?",
                        "Delete Window",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    int index = list.getSelectedIndex();
                    Recipe selectedRecipe = getController().getRecipeList().getRecipeAt(index);
                    getController().getRecipeList().deleteRecipe(selectedRecipe);
                    listModel.remove(index);

                    int size = listModel.getSize();

                    if (size == 0) { //Nobody's left, disable firing.
                        deleteRecipeButton.setEnabled(false);

                    } else { //Select an index.
                        if (index == listModel.getSize()) {
                            //removed item in last position
                            index--;
                        }

                        list.setSelectedIndex(index);
                        list.ensureIndexIsVisible(index);
                    }

                }
            }
        }
    }

    // EFFECTS: Adds a recipe to the recipe list with a given name
    private class AddRecipeAction extends AbstractAction {

        AddRecipeAction() {
            super(ButtonNames.ADD.getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.ADD.getValue())) {
                String recipeEntered = JOptionPane.showInputDialog(null,
                        "What is the name of this recipe?",
                        "New Recipe",
                        JOptionPane.QUESTION_MESSAGE);
                try {
                    if (recipeEntered != null) {
                        Recipe r = new Recipe(recipeEntered, 0);
                        getController().getRecipeList().addRecipe(r);
                        givePrepTime(r);
                    }
                } catch (DuplicateRecipeException exception) {
                    JOptionPane.showMessageDialog(null,
                            "Recipe for " + recipeEntered + " already exists!",
                            "System Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    //EFFECTS: Allows user to add a prep time to a given recipe
    private void givePrepTime(Recipe r) {
        boolean keepGoing = true;
        while (keepGoing) {
            String prepTimeEntered = JOptionPane.showInputDialog(null,
                    "How many minutes does this recipe take to make?",
                    "New Recipe",
                    JOptionPane.QUESTION_MESSAGE);
            if (prepTimeEntered == null) {
                getController().getRecipeList().deleteRecipe(r);
                return;
            }
            try {
                int prepTime = Integer.parseInt(prepTimeEntered);
                r.changePrepTime(prepTime);
                keepGoing = false;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Not a valid time!",
                        "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        addIngredients(r);
    }

    //EFFECTS: Allows user to enter a list of ingredients to a given recipe
    private void addIngredients(Recipe r) {
        boolean keepAdding = true;
        while (keepAdding) {
            String ingredientEntered = JOptionPane.showInputDialog(null,
                    "Enter the ingredient you want to add", "Add Ingredient",
                    JOptionPane.QUESTION_MESSAGE);
            Ingredient i = new Ingredient(ingredientEntered);
            if (ingredientEntered != null && !r.addIngredientToRecipe(i)) {
                showDuplicateError();
            }
            keepAdding = addIngredientFinishRecipeLoop(r);
        }
        listModel.addElement(r.getRecipeName());
    }

    //EFFECTS: Allows user to decide whether to finish a given recipe or add another ingredient to a given recipe
    private boolean addIngredientFinishRecipeLoop(Recipe r) {
        boolean keepAdding = true;
        Object[] options = {"Add another ingredient",
                "Finish recipe"};
        int reply = JOptionPane.showOptionDialog(null,
                "Would you like to add another ingredient or finish recipe?",
                "Add to or Save Recipe", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (reply == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "Recipe for " + r.getRecipeName() + " successfully added!",
                    "Load Status", JOptionPane.PLAIN_MESSAGE);
            keepAdding = false;
        }
        return keepAdding;
    }

    //EFFECTS: shows an error pop-up telling user that "Ingredient already added!"
    private void showDuplicateError() {
        JOptionPane.showMessageDialog(null, "Ingredient already added!",
                "Duplicate Ingredient Error",
                JOptionPane.ERROR_MESSAGE);
    }


}
