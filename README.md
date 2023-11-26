# My Personal Project: "Mom & Pop's"

## What is "Mom & Pop's"?

Mom & Pop's is a multi-functional recipe and ingredient manager that allows people to
consolidate all of their recipes in one place. This app seeks to answer the question
**"What am I going to make tonight."**

## Who will use it?

This application will be used by:
- Students who are no longer living with their parents.
- Busy professionals with "no time" to cook.
- Elderly folk who have begun to forget their recipes.
- People who would like to preserve recipes that have been passed down generations.

As students move out, they will no longer be able to eat food made by their *'mom and pop's'*.
Consequently, this recipe manager will allow students to follow family recipes
and cook their favourite meals away from home. **In the absence of mom and dad,
the app "Mom & Pop's" will help people feed themselves tasty and healthy homecooked meals.**

## Why is this project of interest to me?

From a young age, my mom began teaching me how to cook and bake. I didn't know it then,
but I now recognize that she was teaching me an invaluable skill. As a student living away
from home, I enjoy cooking for myself and emulating my mom's recipes. However, I sometimes
find that I cannot remember certain ingredients that go into making her food. Furthermore, living alone has made me
realize that it is
difficult to manage an inventory of food and keep track of when food expires. **After experiencing
these problems, I am interested in creating an app that may solve them.**

## User Stories

- As a user, I want to be able to create a new recipe with a name, specified ingredients and preparation time,
and add it to a list of recipes.
- As a user, I want to be able to view a list of the titles of the recipes in my collection.
- As a user, I want to be able to delete a recipe from my collection.
- As a user, I want to be able to select a recipe in my collection and view the recipe in detail.

- As a user, when I select the quit option from the application menu, I want to be reminded to save my recipe book
to file and have the option to do so or not.
- As a user, I want to be given the option to load my recipe book from file.

User stories not implemented yet (for future use):
- As a user, I want to be able to add ingredients to a list of ingredients (ie. a virtual pantry).
- As a user, I want to be able to view a list of the titles of ingredients in my pantry.
- As a user, I want to be able to discard ingredients from my virtual pantry.
- As a user, I want to be able to search for recipes that can be made using the ingredients currently
  added to my virtual pantry.

## Instructions for Grader

How to add a recipe to recipe list:
1. From the home page, navigate to Recipes Tab by clicking the "Recipes" button at the top of the window or the
"View Recipes" button in the button panel.
2. Once in recipe page, click "Add New Recipe" button.
3. Enter a name for your recipe, click OK.
4. Enter a prep time for the recipe, click OK.
5. Enter the name of the first ingredient you want to add to the recipe. Click OK.
6. a) To add another ingredient, click "Add another ingredient" and repeat step 5.
<br>
   b) Once all ingredients have been added, click "Finish recipe".
7. Finished recipes should appear in the scroll panel located within the Recipes tab.

How to delete a recipe from recipe list:
1. Within the Recipes tab, click on the title of the recipe you would like to delete.
   (Note: If scroll pane is empty, there are no recipes in the current recipe list and deletion cannot occur.)
2. Once recipe name is highlighted in blue, click the "Delete Recipe" button.
3. Confirm deletion by clicking "Yes" in the pop-up window.

How to view a recipe in detail:
1. Within the Recipes tab, click on the title of the recipe you would like to view.
   (Note: If scroll pane is empty, there are no recipes in the current recipe list and viewing cannot occur.)
2. Once recipe name is highlighted in blue, click the "View Recipe" button. A pop-window with recipe details
will appear.
3. a) To close recipe window, click "Ok, go back".
<br>
   b) To delete the recipe you are currently viewing, click "Delete Recipe".

How to Save:
1. Navigate to Home tab using "Home" button located at the top of the window.
2. Click "Save Recipes".
3. Pop-up window will confirm that you have saved. Click "Ok" to close.

How to Load:
1. Navigate to Home tab using "Home" button located at the top of the window.
2. Click "Load Recipes".
3. Pop-up window will confirm that you have loaded a saved recipe list. Click "Ok" to close.

Alternative Saving and Loading Using Quit:
1. Navigate to Home tab using "Home" button located at the top of the window.
2. Click "Quit". A pop-up window will appear.
3. a) Click "Yes" to save the recipe list and quit.
<br>
   b) Click "No" to quit the app without saving.
<br>
   c) Click "Cancel" to cancel the quitting process and return back to Home tab.

Visual Component:
- A GIF can be found on the Home page.

## Phase 4: Event Log sample

Sun Nov 26 14:30:03 PST 2023
New recipe for Spaghetti created

Sun Nov 26 14:30:03 PST 2023
Recipe for Spaghetti added to recipe list

Sun Nov 26 14:30:04 PST 2023
Prep time for Spaghetti set to 30 minutes

Sun Nov 26 14:30:07 PST 2023
Noodles added to Spaghetti recipe

Sun Nov 26 14:30:10 PST 2023
Tomato Sauce added to Spaghetti recipe

Sun Nov 26 14:30:13 PST 2023
Meatballs added to Spaghetti recipe

Sun Nov 26 14:30:18 PST 2023
Recipe for Spaghetti viewed in detail

Sun Nov 26 14:30:26 PST 2023
Recipe for Spaghetti deleted from recipe list

Sun Nov 26 14:30:28 PST 2023
Recipe List saved

Sun Nov 26 14:30:29 PST 2023
Recipe List loaded

Process finished with exit code 0

## Phase 4: Task 3

If I had more time to work on this project, I would refactor my methods so that they utilize more exceptions.
Since exceptions were taught later in the semester, some of my methods produce false booleans instead of exceptions when
an illegal action is attempted (ie. adding an ingredient to a recipe that already has that ingredient).
To complete this refactoring, I would make the method addIngredientToRecipe(Ingredient i) produce void and throw
an exception DuplicateIngredientException instead of returning false when the user tries to add an ingredient that has
already been added. An exception would make more sense than a boolean
because users only add the same ingredient twice by accident.


