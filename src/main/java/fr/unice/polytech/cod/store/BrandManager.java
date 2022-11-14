package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.CookieBook;
import fr.unice.polytech.cod.ingredient.Cookie;

public class BrandManager {
    StoreManager storeManager;

    public BrandManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    public void validCookie(Cookie cookie, String store) throws Exception{
        this.storeManager.addCookieStore(cookie, store);
    }

    public StoreManager getStoreManager() {
        return this.storeManager;
    }
}
