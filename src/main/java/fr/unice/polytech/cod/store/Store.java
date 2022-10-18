package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Item;
import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Store {

    List<Order> orderList;

    private final Stock stock;

    public Store() {
        this.orderList = new ArrayList<>();
        this.stock = new Stock();
    }

    public void retrieveOrder(Order order) {
        this.orderList.remove(order);
    }

    public boolean hasEnoughIngredients(List<Ingredient> ingredients){
        return true;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = new ArrayList<>(orderList);
    }

    public List<Order> getOrderList() {
        return orderList;
    }


}


