package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CookieBook {
    private final List<Cookie> cookies;

    public CookieBook(){
        cookies = new ArrayList<>();
        cookies.add(new Cookie("Cookie au chocolat",null,null,new ArrayList<>()));
        cookies.add(new Cookie("Cookie à la vanille",null,null,new ArrayList<>()));
        cookies.add(new Cookie("Cookie à la pistache",null,null,new ArrayList<>()));
        // TODO : Add recipes here.
    }

    /**
     * return the availables cookies depend from the store
     * @param store
     * @return a list of cookies
     */
    public List<Cookie> getAvailableCookie(Store store) {
        List<Cookie> cookieAvailable = new ArrayList<>();
        for(Cookie cookie : cookies){
            if(store.hasEnoughIngredients(cookie.getIngredients()))
                cookieAvailable.add(cookie);
        }
        return cookieAvailable;
    }
}
