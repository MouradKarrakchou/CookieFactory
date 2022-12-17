package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.FidelityAccountAlreadyExistException;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.UserAction;
import fr.unice.polytech.cod.interfaces.UserEndpoint;
import fr.unice.polytech.cod.interfaces.UserRequest;
import fr.unice.polytech.cod.pojo.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.schedule.Interval;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserManager implements UserEndpoint {

    @Autowired
    UserAction userAction;
    @Autowired
    UserRequest userRequest;


    public List<Store> viewStoreAvailable(User user) {
        return userRequest.viewStoreAvailable();
    }

    public Store selectStore(User user, String storeName) throws InvalidStoreException {
        return userAction.selectStore(storeName, user.getCart());
    }

    public List<Cookie> viewStoreCatalogue(User user){
        return userRequest.viewCatalog(user.getCart().getStore());
    }

    public boolean addCookieToCart(User user, Cookie cookie, int quantity){
        return userAction.addCookies(cookie, quantity, user.getCart());
    }

    public Bill validateCart(User user) throws Exception {
        return userAction.validateCart(user); //TODO aller corriger les methodes de CartHandler
    }

    public boolean removeCookieFromCart(User user, Cookie cookie, int quantity){
        return userAction.removeCookies(cookie, quantity, user.getCart());
    }

    public List<Interval> getRetrieveCookieHours(User user, int numberOfDayBeforeTheOrder){
        return userRequest.getAvailableIntervals(user.getCart().getStore(), user.getCart(), numberOfDayBeforeTheOrder);
    }
    public void chooseRetrieveCookieHour(User user, Interval interval){
        userAction.chooseInterval(interval, user.getCart());
    }

    public boolean cancelOrder(User user, Order orderToCancel){
        return userAction.cancelOrder(user.getCart(), user.getUserOrders(), orderToCancel);
    }

    public void createFidelityAccount(User user, String name, String email, String password) throws FidelityAccountAlreadyExistException {
        userAction.subscribeToFidelityAccount(user, name, email, password);
    }

    public List<Order> getPreviousOrders(User user) throws Exception {
        return userRequest.getHistory(user.getFidelityAccount());
    }
}
