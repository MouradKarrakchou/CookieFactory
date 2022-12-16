package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.StoreLocation;
import fr.unice.polytech.cod.food.Cookie;

public interface CookieBrandManager {

    void validCookie(BrandManager brandManager, Cookie cookie, String store);

    StoreLocation getStoreLocation(BrandManager brandManager);

    void removeCookie(BrandManager brandManager, Cookie cookie, String store);
}
