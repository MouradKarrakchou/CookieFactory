package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.SurpriseBasket;

import java.util.List;

public interface StoreAccessor {

    /**
     * Return the cookies a store can make based on the stock of the store and the recipes in the store's cookie book.
     *
     * @param store - The store.
     * @return the list of available cookies.
     */
    List<Cookie> getAvailableCookie(Store store);
    List<Order> getObsoleteOrders(Store store);
    List<SurpriseBasket> getAllSurpriseBasket(Store store);
}
