package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import java.util.Set;

public interface CartActions {

    /**
     * If the store as the ingredients, add an item to the cart
     *
     * @param cart - The cart.
     * @param item - The item to add.
     * @return boolean - If the item is add to the cart
     */
    boolean addToCart(Cart cart, Item item);

    /**
     * Remove the given item quantity from the quantity of the item, if the item is present as one copy, then it is deleted.
     *
     * @param cart - The cart.
     * @param item - The item present in the cart.
     */
    void removeFromCart(Cart cart, Item item);

    /**
     * Validate the cart and create an order, that is added to the user and the sore.
     * The cart is validated only if the store has all the ingredients needed
     *
     * @param cart - The cart.
     * @param user - User of the cart.
     * @return The bill of the validate order.
     * @throws Exception - If not has enough ingredients
     */
    Bill validate(Cart cart, User user) throws Exception;

    // Getters
    Set<Ingredient> generateIngredientsNeeded(Cart cart, Set<Item> items);
    Item findItem(Cart cart, String cookieName) throws Exception;
    boolean isEmpty(Cart cart);

    /**
     * Show all the cookies in our order and give the choice to validate or add/delete more cookies
     */
    void showCart(Cart cart);
}
