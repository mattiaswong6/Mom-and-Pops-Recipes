package ui.tabs;

import model.Recipe;
import model.exception.DuplicateRecipeException;
import ui.ButtonNames;
import ui.RecipeAppUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RecipesTab extends Tab {
    private JPanel list;

    //EFFECTS: constructs a recipes tab for console with scroll pane of recipes
    public RecipesTab(RecipeAppUI controller) {
        super(controller);
        setLayout(new BorderLayout());

        list = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        list.add(new JPanel(), gbc);

        add(new JScrollPane(list));
        list.add(new JButton(new AddRecipeAction()));
    }

    // Adds a recipe to the recipe list with a given prep time and set of ingredients
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
                        finishRecipe(r);
                    }
                } catch (DuplicateRecipeException exception) {
                    JOptionPane.showMessageDialog(null, "Recipe already exists",
                            "System Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void finishRecipe(Recipe r) {
        String prepTimeEntered = JOptionPane.showInputDialog(null,
                "How many minutes does this recipe take to make?",
                "New Recipe",
                JOptionPane.QUESTION_MESSAGE);
        try {
            int prepTime = Integer.parseInt(prepTimeEntered);
            r.changePrepTime(prepTime);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not a valid time!",
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
