package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.CartActions;
import fr.unice.polytech.cod.interfaces.ItemActions;
import fr.unice.polytech.cod.interfaces.StockExplorer;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class CartHandler implements CartActions {
    StockExplorer stockExplorer;
    ItemActions itemActions;

    @Autowired
    public CartHandler(StockExplorer stockExplorer, ItemActions itemActions) {
        this.stockExplorer = stockExplorer;
        this.itemActions = itemActions;
    }

    @Override
    public boolean addToCart(Cart cart, Item item) {
        stockExplorer.hasEnoughIngredients(cart.store.stock, itemActions.generateIngredientsNeeded(item));

        if (! .hasEnoughIngredients(item.generateIngredientsNeeded()))
            return false;

        itemList.add(item);
        return true;
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
