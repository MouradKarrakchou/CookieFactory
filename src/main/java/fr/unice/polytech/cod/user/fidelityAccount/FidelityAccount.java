package fr.unice.polytech.cod.user.fidelityAccount;

import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FidelityAccount {
    private final String name;
    private final String email;
    private final String password;
    private int numberOfCommandedCookies;
    private final List<Order> orderList;
    private Discount discount;

    //TODO Ajouter plus tard un UserID
    public FidelityAccount(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberOfCommandedCookies = 0;
        this.orderList = new ArrayList<>();
    }

    public FidelityAccount() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.numberOfCommandedCookies = 0;
        this.orderList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    public List<Order> getOrderList() {
        return orderList;
    }

    public int getNumberOfCommandedCookies() {
        return numberOfCommandedCookies;
    }

    public Discount getTheDiscount() {
        return discount;
    }

    public void setTheDiscount(Discount loyalty_program) {
        discount = loyalty_program;
    }

    public void resetDiscount() {
        this.discount = null;
    }

    public void incrementNumberOfCommandedCookies(int value) {
        numberOfCommandedCookies += value;
    }

    public void decrementNumberOfCommandedCookies(int value) {
        numberOfCommandedCookies -= value;
    }
}
