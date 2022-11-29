package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.data.CookieBook;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.store.Store;
import java.util.List;

public interface CookieBookManager {

    List<Cookie> getAvailableCookie(CookieBook cookieBook, Store store);

    void addCookieRecipe(CookieBook cookieBook, Cookie cookie);

    void removeCookieRecipe(CookieBook cookieBook, Cookie cookieToRemove);

    List<Cookie> getCookies(CookieBook cookieBook);

    Cookie getCookie(CookieBook cookieBook, String cookieName);

}
