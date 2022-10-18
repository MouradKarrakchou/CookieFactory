package fr.unice.polytech.cod;

import java.util.ArrayList;
import java.util.List;

public class CookieBook {
    private final List<Cookie> cookies;

    public CookieBook(){
        cookies = new ArrayList<>();
        cookies.add(new Cookie("Cookie au chocolat",null,null,null));
        cookies.add(new Cookie("Cookie à la vanille",null,null,null));
        cookies.add(new Cookie("Cookie à la pistache",null,null,null));
        // TODO : Add recipes here.
    }

    public List<Cookie> getAvailableCookie() {
        return cookies;
    }
}
