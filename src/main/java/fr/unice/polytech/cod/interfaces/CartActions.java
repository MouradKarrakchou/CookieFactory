package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import java.util.Set;

public interface CartActions {
    // Setters
    boolean addToCart(Cart cart, Item item);
    void removeFromCart(Cart cart, Item item);
    Bill validate(Cart cart, User user) throws Exception;

    // Getters
    Set<Ingredient> generateIngredientsNeeded(Cart cart, Set<Item> items);
    Item findItem(Cart cart, String cookieName) throws Exception;
    boolean isEmpty(Cart cart);
}
