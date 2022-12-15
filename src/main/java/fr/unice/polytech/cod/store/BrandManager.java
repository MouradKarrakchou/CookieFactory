package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.pojo.StoreLocation;
import fr.unice.polytech.cod.food.Cookie;

public class BrandManager {
    StoreLocation storeLocation;

    public BrandManager(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public void validCookie(Cookie cookie, String store) throws Exception{
        this.storeLocation.addCookieStore(cookie, store);
    }

    public StoreLocation getStoreLocation() {
        return this.storeLocation;
    }

    public void removeCookie(Cookie cookie, String store) throws Exception{
        this.storeLocation.removeCookieStore(cookie, store);
    }
}
