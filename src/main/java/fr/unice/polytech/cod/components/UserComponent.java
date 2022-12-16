package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.UserAction;
import fr.unice.polytech.cod.interfaces.UserRequest;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserComponent {

    @Autowired
    UserAction userAction;
    @Autowired
    UserRequest userRequest;

    User user;

    public UserComponent(){
        user = new User();
    }

    public List<Store> viewStoreAvailable() {
        return userRequest.viewStoreAvailable();
    }

    public Store selectStore(String storeName) throws InvalidStoreException {
        return userAction.selectStore(storeName, user.getCart());
    }

    public List<Cookie> viewStoreCatalogue(){
        return userRequest.viewCatalog(user.getStore());
    }

    public boolean addCookieToCart(Cookie cookie, int quantity){
        return userAction.addCookies(cookie, quantity, user.getCart());
    }

    public Bill validateCart() throws Exception {
        return userAction.validateCart(user, user.getCart()); //TODO aller corriger les methodes de CartHandler
    }

    public boolean removeCookieFromCart(Cookie cookie, int quantity){
        return userAction.removeCookies(cookie, quantity, user.getCart());
    }

    public List<Interval> getRetrieveCookieHours(int numberOfDayBeforeTheOrder){
        return userRequest.getAvailableIntervals(user.getStore(), user.getCart(), numberOfDayBeforeTheOrder);
    }

    public boolean cancelOrder(Order orderToCancel){
        return userAction.cancelOrder(user.getCart(), user.getOrders(), orderToCancel);
    }


}
