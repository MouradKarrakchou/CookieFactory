package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Order;

import java.util.ArrayList;
import java.util.List;

public class Store {

    List<Order> orderList;

    public Store() {
        this.orderList = new ArrayList<>();
    }

    public void retrieveOrder(Order order) {
        this.orderList.remove(order);
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = new ArrayList<>(orderList);
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
