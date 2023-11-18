package ui.tabs;

import ui.ButtonNames;
import ui.RecipeAppUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Welcome to Mom and Pop's Recipes!";
    private JLabel greeting;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(RecipeAppUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

        placeGreeting();
        addButtonPanel();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,2));
        buttonPanel.add(new JButton(new ViewRecipesAction()));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));
        buttonPanel.add(new JButton((new QuitAction())));

        this.add(buttonPanel);
    }


    /**
     * Represents the action to be taken when the user wants to view all recipes.
     * Switches user to Recipes Tab.
     */
    private class ViewRecipesAction extends AbstractAction {

        ViewRecipesAction() {
            super(ButtonNames.VIEW_RECIPES.getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.VIEW_RECIPES.getValue())) {
                getController().getTabbedPane().setSelectedIndex(RecipeAppUI.RECIPES_TAB_INDEX);
            }
        }
    }

    /**
     * Represents the action to be taken when the user wants to save their recipes.
     *
     */
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super(ButtonNames.SAVE.getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.SAVE.getValue())) {
                String returnedMessage = getController().saveRecipeList();
                JOptionPane.showMessageDialog(null, returnedMessage,
                        "Save Status", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    /**
     * Represents the action to be taken when the user wants to load their saved recipes.
     *
     */
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super(ButtonNames.LOAD.getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.LOAD.getValue())) {
                String returnedMessage = getController().loadRecipeList();
                JOptionPane.showMessageDialog(null, returnedMessage,
                        "Load Status", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    /**
     * Represents the action to be taken when the user wants to quit the app.
     *
     */
    private class QuitAction extends AbstractAction {

        QuitAction() {
            super(ButtonNames.QUIT.getValue());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String buttonPressed = e.getActionCommand();
            if (buttonPressed.equals(ButtonNames.QUIT.getValue())) {
                int reply = JOptionPane.showConfirmDialog(null,
                        "Would you like to save before quitting?",
                        "Quit Window", JOptionPane.YES_NO_CANCEL_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    String returnedMessage = getController().saveRecipeList();
                    System.exit(0);
                } else if (reply == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        }
    }

}