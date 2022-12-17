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
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;

import java.util.List;

public interface UserAction {

    boolean addCookies(Cookie cookie, int quantity, Cart cart);
    boolean removeCookies(Cookie cookie, int quantity, Cart cart);
    Store selectStore(String name, Cart cart) throws InvalidStoreException;

    void chooseInterval(Interval interval, Cart cart);

    Bill validateCart(User user) throws Exception;

    void removeOneItemFromCart(Item item, Cart cart);

    void subscribeToFidelityAccount(User user, String name, String email, String password) ;
    boolean cancelOrder(Cart cart, List<Order> userOrders, Order orderToCancel);

}
