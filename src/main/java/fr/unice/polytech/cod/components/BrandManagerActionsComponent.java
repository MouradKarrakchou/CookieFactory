package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.BrandManagerActions;
import fr.unice.polytech.cod.interfaces.StoreFinder;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import org.springframework.beans.factory.annotation.Autowired;

public class BrandManagerActionsComponent implements BrandManagerActions {
    @Autowired
    StoreFinder storeFinder;
    @Autowired
    StoreModifier storeModifier;
    @Override
    public void validCookie(Cookie cookie, String store) throws Exception {
        storeModifier.addCookieStore(storeFinder.findStore(store),cookie);
    }

    @Override
    public void removeCookie(Cookie cookie, String store) throws Exception {
        storeModifier.removeCookieStore(storeFinder.findStore(store),cookie);
    }
}
