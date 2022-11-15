package fr.unice.polytech.cod.decorator_pattern;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import java.util.ArrayList;

public class PartyCookie extends PartyCookieComponent {
       public PartyCookie(Cookie cookie, CookieSize cookieSize) {
            super.cookie = cookie;
            super.cookieSize = cookieSize;
       }

    @Override
    public String toString() {
        return "CookieComponent{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", preparationTime=" + getPreparationTime() +
                ", ingredients=" + getPartyCookieIngredients() +
                ", cookieSize=" + getCookieSize() +
                '}';
    }
}
