package fr.unice.polytech.cod.pojo.user;

import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.FidelityAccount;

import java.util.*;

public class User {
    private Cart cart;
    private List<Order> userOrders;
    private FidelityAccount fidelityAccount;

    public User() {
        this.cart = new Cart();
        this.userOrders = new ArrayList<>();
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

    public FidelityAccount getFidelityAccount() {
        return fidelityAccount;
    }

    public void setFidelityAccount(FidelityAccount fidelityAccount) {
        this.fidelityAccount = fidelityAccount;
    }
}
