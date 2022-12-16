package fr.unice.polytech.cod.user;

import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.pojo.StoreLocation;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;

import java.time.Instant;
import java.util.*;

public class User {
    private Cart cart;
    private List<Order> userOrders;
    private StoreLocation storeLocation;
    private FidelityAccount fidelityAccount;

    public User(Cart cart, List<Order> userOrders, StoreLocation storeLocation, FidelityAccount fidelityAccount) {
        this.cart = cart;
        this.userOrders = userOrders;
        this.storeLocation = storeLocation;
        this.fidelityAccount = fidelityAccount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<Order> userOrders) {
        this.userOrders = userOrders;
    }

    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public FidelityAccount getFidelityAccount() {
        return fidelityAccount;
    }

    public void setFidelityAccount(FidelityAccount fidelityAccount) {
        this.fidelityAccount = fidelityAccount;
    }
}
