package ui;

public enum ButtonNames {
    VIEW_RECIPES("View Recipes"),
    SAVE("Save"),
    LOAD("Load"),
    QUIT("Quit"),
    SAVE_QUIT("Yes, save and quit"),
    DONT_SAVE_QUIT("No, don't save and quit"),
    GO_BACK("Go back to menu");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
