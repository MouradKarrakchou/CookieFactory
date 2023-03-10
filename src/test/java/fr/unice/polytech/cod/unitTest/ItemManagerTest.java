package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.pojo.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.IngredientActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemManagerTest {

    @Autowired
    IngredientActions ingredientActions;

    Ingredient ingredient;

    @BeforeEach
    void init() {
        this.ingredient = new Ingredient("Sucre", 1, 0);
    }


    @Test
    void test() {
        ingredientActions.increaseQuantity(this.ingredient, 10);
        assertEquals(10, this.ingredient.getQuantity());
    }

}