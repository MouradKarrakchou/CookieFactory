package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.store.BrandManager;

public interface CookieBrandManager {

    void validCookie(BrandManager brandManager, Cookie cookie, String store);

    StoreLocation getStoreLocation(BrandManager brandManager);

    void removeCookie(BrandManager brandManager, Cookie cookie, String store);
}
