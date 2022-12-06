package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.CartActions;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CartHandler implements CartActions {
    @Override
    public boolean addToCart(Cart cart, Item item) {
        return false; // TODO
    }

    @Override
    public void removeFromCart(Cart cart, Item item) {
        // TODO
    }

    @Override
    public Bill validate(Cart cart) throws Exception {
        return null; // TODO
    }

    @Override
    public Store getStore(Cart cart) {
        return null; // TODO
    }

    @Override
    public Set<Ingredient> generateIngredientsNeeded(Cart cart) {
        return null; // TODO
    }

    @Override
    public List<Item> getItemList(Cart cart) {
        return null; // TODO
    }

    @Override
    public Item findItem(Cart cart, String cookieName) {
        return null; // TODO
    }

    @Override
    public boolean isEmpty(Cart cart) {
        return false; // TODO
    }
}
