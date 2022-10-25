package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Store store;
    private List<Item> itemList;
    private boolean isValidated;


    public Cart(Store store) {
        this.store = store;
        this.itemList = new ArrayList<>();
        this.isValidated = false;
    }

    public void showCart() {
        Display.showItems(itemList);
    }

    public boolean addToCart(Item item) {
        itemList.add(item);
        return true;
    }

    public void removeToCart(Item item) {
        itemList.remove(item);
    }

    public Order createOrder(User user) {
        return new Order(this, OrderState.PENDING,user);
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public boolean validateCart(){
        this.isValidated = true;
        return true;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean b) {
        this.isValidated = b;
    }

    public boolean isEmpty() {
        return this.itemList.isEmpty();
    }
}
