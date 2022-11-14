package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.data.StoreManager;
import fr.unice.polytech.cod.food.Cookie;

public class BrandManager {
    StoreManager storeManager;

    public BrandManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    public void validCookie(Cookie cookie, String store) throws Exception{
        this.storeManager.addCookieStore(cookie, store);
    }

    public void removeCookie(Cookie cookie, String store) throws Exception{
        this.storeManager.removeCookieStore(cookie, store);
    }

    public StoreManager getStoreManager() {
        return this.storeManager;
    }
}
