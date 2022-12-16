package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.ingredient.Ingredient;

public interface IngredientActions {

    void multiplyQuantity(Ingredient ingredient, int n);

    void increaseQuantity(Ingredient ingredient, double quantity);
}
