package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import java.util.List;
import java.util.Set;

public interface ItemActions {
    // Setters
    void updateQuantity(Item item, int value);

    // Getters
    Set<Ingredient> generateIngredientsNeeded(Item item);
    Set<Ingredient> generateIngredientsNeeded(Set<Item> items);

    Set<Ingredient> generateIngredientsNeeded(List<Item> items);
}
