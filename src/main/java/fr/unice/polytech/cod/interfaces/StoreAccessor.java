package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;

import java.util.List;

public interface StoreAccessor {
    /**
     * For a given store, retrieve all available cookie according to the ingredients in stock
     * @param store The given store to check available cookie
     * @return The list of available cookie
     */
    List<Cookie> getAvailableCookie(Store store);
    List<Order> getObsoleteOrders(Store store);
}
