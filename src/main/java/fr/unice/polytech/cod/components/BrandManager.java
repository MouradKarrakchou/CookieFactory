package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.BrandManagerActions;
import fr.unice.polytech.cod.interfaces.IStoreFinder;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandManager implements BrandManagerActions {
    @Autowired
    IStoreFinder IStoreFinder;
    @Autowired
    StoreModifier storeModifier;
    @Override
    public void validCookie(Cookie cookie, String store) throws Exception {
        storeModifier.addCookieStore(IStoreFinder.findStore(store),cookie);
    }

    @Override
    public void removeCookie(Cookie cookie, String store) throws Exception {
        storeModifier.removeCookieStore(IStoreFinder.findStore(store),cookie);
    }
}
