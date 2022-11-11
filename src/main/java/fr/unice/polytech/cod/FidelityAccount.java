package fr.unice.polytech.cod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FidelityAccount {
    private String name;
    private String email;
    private String password;
    private int numberOfCommandedCookies;
    private List<Order> orderList;
    private Optional<Discount> discount;


    //TODO Ajouter plus tard un UserID
    public FidelityAccount(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberOfCommandedCookies = 0;
        this.orderList =  new ArrayList<>();
        this.discount = Optional.empty();
    }
    public FidelityAccount(){
        this.name = "";
        this.email = "";
        this.password = "";
        this.numberOfCommandedCookies = 0;
        this.orderList = new ArrayList<>();
        this.discount = Optional.empty();
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

    public void addOrder(Order order) {
        this.orderList.add(order);
        List<Item> items = order.getCart().getItemList();
        for(Item item : items) numberOfCommandedCookies+= item.getQuantity();
        if(numberOfCommandedCookies >= 30){
            this.discount = Optional.of(new Discount("Loyalty program",10));
            numberOfCommandedCookies-=30;
        }
    }
    public Optional<Discount> resetDiscount() {
        return this.discount = Optional.empty();
    }
    public Optional<Discount> getDiscount() {
        return this.discount;
    }

}
