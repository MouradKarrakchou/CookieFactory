package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;

public interface StoreAction {
    void checkObsoleteOrders(Store store);

    /**
     * Add the order to the obsolete orders list
     * @param order to add
     */
    void addToObsoleteOrders(Store store,Order order);
}
