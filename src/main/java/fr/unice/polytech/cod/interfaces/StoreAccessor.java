package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.store.Store;

import java.util.List;

public interface StoreAccessor {
    List<Cookie> getAvailableCookie(Store store);
}
