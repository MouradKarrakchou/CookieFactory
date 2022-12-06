package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.CartActions;
import fr.unice.polytech.cod.interfaces.ItemActions;
import fr.unice.polytech.cod.interfaces.StockExplorer;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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

    /**
     * If the store as the ingredients, add an item to the cart
     *
     * @param item
     * @return boolean
     */
    @Override
    public boolean addToCart(Cart cart, Item item) {
        Optional<Item> _item =  cart.itemSet.stream()
                .filter(currentItem -> currentItem.equals(item)).findFirst();

        if (_item.isPresent())
            _item.get().updateQuantity(item.getQuantity());
        else
            cart.itemSet.add(item);

        if(!stockExplorer.hasEnoughIngredients(cart.store.stock, itemActions.generateIngredientsNeeded(cart.itemSet))){
            removeFromCart(cart, item);
            return false;
        }

        return true;
    }

    @Override
    public void removeFromCart(Cart cart, Item item) {
        Optional<Item> _item =  cart.itemSet.stream().filter(currentItem -> currentItem.equals(item)).findFirst();
        if (_item.isEmpty())
            return;

        Item inCartItem = _item.get();
        inCartItem.updateQuantity(- item.getQuantity());
    }

    @Override
    public Bill validate(Cart cart, User user) throws Exception {
        Set<Ingredient> ingredientsNeeded = itemActions.generateIngredientsNeeded(cart.itemSet);
        if (!cart.store.hasEnoughIngredients(ingredientsNeeded))
            throw new Exception("Ingrédients indisponibles");

        Order order = new Order(cart, user);
        if (user.hasFidelityAccount())
            user.useDiscount(order);
        user.addOrder(order);
        cart.store.addOrder(order, ingredientsNeeded);

        cart.interval.validate(order);
        cart.itemSet.clear();

        return new Bill(order);
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
