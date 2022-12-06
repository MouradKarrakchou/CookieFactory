package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;

import java.util.List;
import java.util.Set;

public interface CartActions {
    // Setters
    boolean addToCart(Cart cart, Item item);
    void removeFromCart(Cart cart, Item item);
    Bill validate(Cart cart, User user) throws Exception;

    // Getters
    Store getStore(Cart cart);
    Set<Ingredient> generateIngredientsNeeded(Cart cart);
    List<Item> getItemList(Cart cart);
    Item findItem(Cart cart, String cookieName);
    boolean isEmpty(Cart cart);
}
