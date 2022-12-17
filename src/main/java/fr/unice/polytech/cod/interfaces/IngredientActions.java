package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.ingredient.Ingredient;

public interface IngredientActions {

    /**
     *  Multiply the quantity by n. Used in Item to get the total of quantity needed for n cookies.
     *
     * @param ingredient - The ingredient.
     * @param n - The number to multiply the quantity by.
     */
    void multiplyQuantity(Ingredient ingredient, int n);

    /**
     * Sum the quantity by n.
     *
     * @param ingredient - The ingredient.
     * @param quantity - The number to sum the quantity by.
     */
    void increaseQuantity(Ingredient ingredient, double quantity);
}
