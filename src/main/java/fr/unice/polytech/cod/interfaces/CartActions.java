package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.user.Cart;

import java.util.Set;

public interface CartActions {
    // Setters
    boolean addToCart(Cart cart, Item item);

    // Getters
    Set<Ingredient> generateIngredientsNeeded(Cart cart);
}
