package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;

public interface UserAction {

    boolean chooseCookies(User user, Cookie cookie, int quantity, Cart cart);

    Store selectStore(String name, Cart cart) throws InvalidStoreException;

    void chooseInterval(User user, Interval interval);
    Bill validateCart(User user) throws Exception;
    void addOrder(User user, Order order);
    void removeOneItemFromCart(User user, Item item);
    void subscribeToFidelityAccount(User user, String name, String email, String password) ;
    void useDiscount(User user, Order order);
    boolean cancelOrder(User user, Order order);

}
