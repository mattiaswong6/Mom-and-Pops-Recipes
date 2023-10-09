package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {
    private Ingredient testIngredient1;

    @BeforeEach
    void runBefore() {
        testIngredient1 = new Ingredient("Salt");
    }

    @Test
    void testConstructor() {
        assertEquals("Salt", testIngredient1.getIngredientName());
    }

}
