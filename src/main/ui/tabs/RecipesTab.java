package ui.tabs;

import ui.RecipeAppUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class RecipesTab extends Tab implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private JButton viewButton;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField recipeName;

    //EFFECTS: constructs a recipes tab for console with scroll pane of recipes
    public RecipesTab(RecipeAppUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

//        listModel = new DefaultListModel();
//        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        list.setSelectedIndex(0);
//        list.addListSelectionListener(this);
//        list.setVisibleRowCount(5);
//        JScrollPane listScrollPane = new JScrollPane(list);
    }



    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
