package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.pojo.CookieBook;
import fr.unice.polytech.cod.food.Cookie;

import java.util.List;

public interface ICookieBookManager {
    /**
     * Add a cookie to the cookie book if it's not in the book and all the ingredients are in the catalog.
     *
     * @param cookie The given cookie
     */
    void addCookieRecipe(CookieBook cookieBook, Cookie cookie) throws CookieAlreadyExistingException, NotMatchingCatalogRequirementException;

    /**
     * Remove a cookie to the cookie book
     *
     * @param cookieToRemove Yhe given cookie
     */
    void removeCookieRecipe(CookieBook cookieBook, Cookie cookieToRemove);

    List<Cookie> getCookies(CookieBook cookieBook);

    /**
     * Find a Dough with a cookie name in the book.
     *
     * @param cookieName The name of the cookie
     * @return The Cookie with the given name
     */
    Cookie getCookie(CookieBook cookieBook, String cookieName);
}
