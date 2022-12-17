package fr.unice.polytech.cod.pojo;

import fr.unice.polytech.cod.food.Cookie;
import java.util.ArrayList;
import java.util.List;

public class CookieBook {
    private final List<Cookie> cookies;

    public CookieBook() {
        cookies = new ArrayList<>();
    }

    public List<Cookie> getCookies() {
        return cookies;
    }
}
