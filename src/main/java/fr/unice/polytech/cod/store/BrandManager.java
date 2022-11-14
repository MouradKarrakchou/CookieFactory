package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.food.Cookie;

public class BrandManager {
    StoreLocation storeLocation;

    public BrandManager(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public void validCookie(Cookie cookie, String store) throws Exception{
        this.storeLocation.addCookieStore(cookie, store);
    }

    public StoreLocation getStoreManager() {
        return this.storeLocation;
    }
}
