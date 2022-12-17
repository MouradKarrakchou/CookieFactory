package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.exceptions.FidelityAccountAlreadyExistException;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.pojo.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.User;

import java.util.List;

public interface UserEndpoint {
    List<Store> viewStoreAvailable(User user);

    Store selectStore(User user, String storeName) throws InvalidStoreException ;

    List<Cookie> viewStoreCatalogue(User user);

    boolean addCookieToCart(User user, Cookie cookie, int quantity);

    Bill validateCart(User user) throws Exception ;

    boolean removeCookieFromCart(User user, Cookie cookie, int quantity);

    List<Interval> getRetrieveCookieHours(User user, int numberOfDayBeforeTheOrder);
    void chooseRetrieveCookieHour(User user, Interval interval);
    boolean cancelOrder(User user, Order orderToCancel);
    void createFidelityAccount(User user, String name, String email, String password) throws FidelityAccountAlreadyExistException;

    List<Order> getPreviousOrders(User user) throws Exception;
}
