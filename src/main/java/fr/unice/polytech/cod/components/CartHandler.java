package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.CartActions;
import fr.unice.polytech.cod.interfaces.ItemActions;
import fr.unice.polytech.cod.interfaces.StockExplorer;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CartHandler implements CartActions {
    StockExplorer stockExplorer;
    ItemActions itemActions;

    /*
    @Autowired
    public CartHandler(StockExplorer stockExplorer, ItemActions itemActions) {
        this.stockExplorer = stockExplorer;
        this.itemActions = itemActions;
    }
    */

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
    public Set<Ingredient> generateIngredientsNeeded(Cart cart, Set<Item> items) {
        Set<Ingredient> neededIngredients = new HashSet<>();
        // Check the list of items
        for (Item item : items) {
            // Generating all needed ingredients for each item
            for (Ingredient ingredient : item.generateIngredientsNeeded()) {
                // Merging all needed ingredients together
                boolean isAdded = false;
                for (Ingredient neededIngredient : neededIngredients) {
                    if (neededIngredient.equals(ingredient)) {
                        neededIngredient.increaseQuantity(ingredient.getQuantity());
                        isAdded = true;
                    }
                }
                if (!isAdded)
                    neededIngredients.add(ingredient);
            }
        }
        return neededIngredients;
    }

    @Override
    public Item findItem(Cart cart, String cookieName) throws Exception {
        Item itemFounded = cart.itemSet.stream()
                .filter(item -> cookieName.equals(item.getCookie().getName()))
                .findAny()
                .orElse(null);
        if (itemFounded == null)
            throw new Exception("Can't find this item into the cart: " + cookieName);
        else
            return itemFounded;
    }

    @Override
    public boolean isEmpty(Cart cart) {
        return cart.itemSet.isEmpty();
    }
}
