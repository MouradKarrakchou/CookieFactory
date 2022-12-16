package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.CartActions;
import fr.unice.polytech.cod.interfaces.StoreFinder;
import fr.unice.polytech.cod.interfaces.UserAction;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserActionComponent implements UserAction {

    @Autowired
    CartActions cartActions;

    @Autowired
    StoreFinder storsFinder;

    public UserActionComponent(){}
    /**
     * Add cookies to cart
     *
     * @param cookie The cookie to add to the cart
     * @param quantity The quantity of the selected cookie
     */
    @Override
    public boolean chooseCookies(User user, Cookie cookie, int quantity, Cart cart) {
        return cartActions.addToCart(cart, new Item(cookie, quantity));
    }

    /**
     * choose the store for his current order
     *
     * @param name
     * @return
     * @throws InvalidStoreException
     */
    @Override
    public Store selectStore(String name, Cart cart) throws InvalidStoreException {
        Store store = storsFinder.findStore(name);
        cart.setStore(store);
        return (store);    }

    @Override
    public void chooseInterval(User user, Interval interval) {

    }

    @Override
    public Bill validateCart(User user) throws Exception {
        return null;
    }

    @Override
    public void addOrder(User user, Order order) {

    }

    @Override
    public void removeOneItemFromCart(User user, Item item) {

    }

    @Override
    public void subscribeToFidelityAccount(User user, String name, String email, String password) {

    }

    @Override
    public void useDiscount(User user, Order order) {

    }

    @Override
    public boolean cancelOrder(User user, Order order) {
        return false;
    }
}
