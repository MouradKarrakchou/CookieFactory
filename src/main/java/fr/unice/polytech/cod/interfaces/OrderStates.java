package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;

import java.util.List;

public interface OrderStates {
    public void retrieveOrder(List<Order> orderList,Bill bill) throws Exception;

}
