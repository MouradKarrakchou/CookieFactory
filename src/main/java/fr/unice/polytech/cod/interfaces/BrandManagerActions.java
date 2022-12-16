package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Cookie;

public interface BrandManagerActions {

    void validCookie(Cookie cookie, String store) throws Exception;

    void removeCookie(Cookie cookie, String store) throws Exception;

}
