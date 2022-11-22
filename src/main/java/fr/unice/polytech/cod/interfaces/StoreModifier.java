package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.store.Store;

public interface StoreModifier {
    void addCookie(Store store, Cookie cookie) throws NotMatchingCatalogRequirementException, CookieAlreadyExistingException;
    void removeCookie(Store store, Cookie cookie);
}
