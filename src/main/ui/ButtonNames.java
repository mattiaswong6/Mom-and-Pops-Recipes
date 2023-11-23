package ui;

public enum ButtonNames {
    VIEW_RECIPES("View Recipes"),
    SAVE("Save Recipes"),
    LOAD("Load Recipes"),
    QUIT("Quit"),
    ADD("Add New Recipe"),
    DELETE("Delete Recipe"),
    VIEW("View Recipe");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
