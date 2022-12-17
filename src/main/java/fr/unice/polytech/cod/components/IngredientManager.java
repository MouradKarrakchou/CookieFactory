package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.pojo.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.IngredientActions;
import org.springframework.stereotype.Component;

@Component
public class IngredientManager implements IngredientActions {
    @Override
    public void multiplyQuantity(Ingredient ingredient, int n) {
        ingredient.setQuantity(n);
    }

    @Override
    public void increaseQuantity(Ingredient ingredient, double quantity) {
        ingredient.setQuantity(ingredient.getQuantity() + quantity);
    }

}