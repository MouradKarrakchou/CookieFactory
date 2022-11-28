package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import java.util.List;
import java.util.Set;

public interface ItemActions {
    // Setters
    void updateQuantity(Item item, int value);

    // Getters
    int getQuantity(Item item);
    Cookie getCookie(Item item);
    Set<Ingredient> generateIngredientsNeeded(Item item);
    Set<Ingredient> generateIngredientsNeeded(List<Item> items);
}
