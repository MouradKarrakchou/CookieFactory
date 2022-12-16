package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.User;

import java.util.List;

public interface UserEndpoint {
    public List<Store> viewStoreAvailable(User user);

    public Store selectStore(User user, String storeName) throws InvalidStoreException ;

    public List<Cookie> viewStoreCatalogue(User user);

    public boolean addCookieToCart(User user, Cookie cookie, int quantity);

    public Bill validateCart(User user) throws Exception ;

    public boolean removeCookieFromCart(User user, Cookie cookie, int quantity);

    public List<Interval> getRetrieveCookieHours(User user, int numberOfDayBeforeTheOrder);
    public boolean cancelOrder(User user, Order orderToCancel);
    public void createFidelityAccount(User user, String name, String email, String password);

    public List<Order> getPreviousOrders(User user) throws Exception;
}
