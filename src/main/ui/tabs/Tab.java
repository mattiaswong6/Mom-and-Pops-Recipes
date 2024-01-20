package ui.tabs;

import javax.swing.*;

import model.Recipe;
import ui.RecipeAppUI;

import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;


public abstract class Tab extends JPanel {
    private final RecipeAppUI controller;

    //REQUIRES: RecipeAppUI controller that holds this tab
    public Tab(RecipeAppUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel(new GridLayout(10, 1, 10, 5));

        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the RecipeAppUI controller for this tab
    public RecipeAppUI getController() {
        return controller;
    }



}
