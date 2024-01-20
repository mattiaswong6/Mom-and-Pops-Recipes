package model.exception;

import model.Recipe;

public class DuplicateRecipeException extends Exception {

    public DuplicateRecipeException(Recipe recipe) {
        super("There is already a recipe called " + recipe.getRecipeName() + " added.");
    }

}
