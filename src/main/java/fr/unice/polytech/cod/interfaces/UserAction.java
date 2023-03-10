package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.exceptions.FidelityAccountAlreadyExistException;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.pojo.order.Bill;
import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.schedule.Interval;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.Cart;
import fr.unice.polytech.cod.pojo.user.User;

import java.util.List;

public interface UserAction {

    boolean addCookies(Cookie cookie, int quantity, Cart cart);
    boolean removeCookies(Cookie cookie, int quantity, Cart cart);
    Store selectStore(String name, Cart cart) throws InvalidStoreException;

    void chooseInterval(Interval interval, Cart cart);

    Bill validateCart(User user) throws Exception;

    void subscribeToFidelityAccount(User user, String name, String email, String password) throws FidelityAccountAlreadyExistException;
    boolean cancelOrder(Cart cart, List<Order> userOrders, Order orderToCancel);

}
