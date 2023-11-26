package ui.tabs;

import model.EventLog;
import ui.ButtonNames;
import ui.RecipeAppUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

import static java.awt.event.WindowEvent.WINDOW_CLOSING;

public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Welcome to Mom and Pop's Recipes!";
    private JLabel greeting;

    //EFFECTS: constructs a home tab for console with buttons, a greeting, and a GIF
    public HomeTab(RecipeAppUI controller) {
        super(controller);

        setLayout(new GridLayout(3,1));

        placeGif();
        placeGreeting();
        addButtonPanel();
    }

    // MODIFIES: this
    // EFFECTS: creates a GIF at the top of the panel
    private void placeGif() {
        Icon imgIconTwo = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("noodles.gif")));
        JLabel labelTwo = new JLabel(imgIconTwo, JLabel.CENTER);
        this.add(labelTwo);
    }

    //MODIFIES: this
    //EFFECTS: creates greeting in the middle of the panel
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setFont(new Font("Serif", Font.PLAIN, 40));
        this.add(greeting);
    }

    //MODIFIES: this
    //EFFECTS: creates View Recipes, Save, Load, Quit buttons at bottom of the panel
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,2));
        buttonPanel.add(new JButton(new ViewRecipesAction()));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));
        buttonPanel.add(new JButton((new QuitAction())));

        this.add(buttonPanel);
    }



//     EFFECTS: Switches user to Recipes tab
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


    //EFFECTS: saves the current recipe list to recipeList.json
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


    //EFFECTS: loads the recipe list in recipeList.json
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

    //EFFECTS: prompts user to save or not save before quitting the app
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
                    getController().saveRecipeList();
                    System.exit(0);
                } else if (reply == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        }
    }

}