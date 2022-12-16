package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import java.util.List;
import java.util.Set;

public interface ItemActions {

    /**
     * Increase the quantity by a given value.
     *
     * @param item - The item.
     * @param value - The value to increase the quantity.
     */
    void updateQuantity(Item item, int value);

    /**
     * Generate a set of all needed ingredients according to the cookie on the list and its quantity.
     *
     * @param item - The item.
     * @return the set of needed ingredients
     */
    Set<Ingredient> generateIngredientsNeeded(Item item);
    Set<Ingredient> generateIngredientsNeeded(Set<Item> items);
}
