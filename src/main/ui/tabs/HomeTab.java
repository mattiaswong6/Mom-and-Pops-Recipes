package ui.tabs;

import ui.ButtonNames;
import ui.RecipeAppUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Welcome to Mom and Pop's Recipes!";
    private JLabel greeting;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(RecipeAppUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

        placeGreeting();
        placeViewRecipesButton();
//        placeSaveButton();
    }

    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // EFFECTS: creates View Recipes button that switches to teh report tab on the console
    private void placeViewRecipesButton() {
        JButton b1 = new JButton(ButtonNames.VIEW_RECIPES.getValue());

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.VIEW_RECIPES.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(RecipeAppUI.RECIPES_TAB_INDEX);
                }
            }
        });

        this.add(buttonRow);
    }

//    private void placeSaveButton() {
//        JButton b2 = new JButton(ButtonNames.SAVE.getValue());
//
//
//
//    }



}
