package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.ICatalogExplorer;
import fr.unice.polytech.cod.interfaces.ICookieBookManager;
import fr.unice.polytech.cod.pojo.CookieBook;
import fr.unice.polytech.cod.pojo.IngredientCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CookieBookManager implements ICookieBookManager {
    @Autowired
    private ICatalogExplorer ICatalogExplorer;

    @Override
    public void addCookieRecipe(CookieBook cookieBook, Cookie cookie) throws CookieAlreadyExistingException, NotMatchingCatalogRequirementException {
        if (!cookieBook.getCookies().contains(cookie)) {
            for (Ingredient ingredient : cookie.getIngredients()) {
                if(!ICatalogExplorer.isInCatalog(IngredientCatalog.instance, ingredient))
                    throw new NotMatchingCatalogRequirementException();
            }
            cookieBook.getCookies().add(cookie);
        } else {
            throw new CookieAlreadyExistingException();
        }
    }

    @Override
    public void removeCookieRecipe(CookieBook cookieBook, Cookie cookieToRemove) {
        cookieBook.getCookies().removeIf(cookie -> cookie.equals(cookieToRemove));
    }

    @Override
    public List<Cookie> getCookies(CookieBook cookieBook) {
        return null;
    }

    @Override
    public Cookie getCookie(CookieBook cookieBook, String cookieName) {
        for (Cookie cookie : cookieBook.getCookies()) {
            if (cookie.getName().equals(cookieName)) {
                return new Cookie(cookie);
            }
        }
        return null;
    }
}
