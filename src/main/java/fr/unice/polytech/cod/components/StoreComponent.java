package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import fr.unice.polytech.cod.store.Store;

public class StoreComponent implements StoreModifier {
    @Override
    public void addCookie(Store store, Cookie cookie) throws NotMatchingCatalogRequirementException, CookieAlreadyExistingException {

    }

    @Override
    public void removeCookie(Store store, Cookie cookie) {

    }
}
