package fr.unice.polytech.cod;

import java.util.ArrayList;
import java.util.List;

public class FidelityAccount {
    private String name;
    private String email;
    private String password;
    private int numberOfCommandedCookies;
    private List<Order> orderList;

    //TODO Ajouter plus tard un UserID
    public FidelityAccount(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberOfCommandedCookies = 0;
        this.orderList =  new ArrayList<>();
    }
    public FidelityAccount(){
        this.name = "";
        this.email = "";
        this.password = "";
        this.numberOfCommandedCookies = 0;
        this.orderList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getNumberOfCommandedCookies() {
        return numberOfCommandedCookies;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

}
