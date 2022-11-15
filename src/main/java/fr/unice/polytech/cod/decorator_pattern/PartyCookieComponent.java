package fr.unice.polytech.cod.decorator_pattern;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.*;

import java.util.ArrayList;

public abstract class PartyCookieComponent{
    public enum CookieSize {
        L , XL, XXL
    }
    protected Cookie cookie;
    protected CookieSize cookieSize;

    public String getName() {
        return cookie.getName();
    }
    public double getPrice() {
        return cookie.getPriceHT();
    }
    public ArrayList<Ingredient> getPartyCookieIngredients() {
        return cookie.getIngredientsList();
    }

    public CookieSize getCookieSize() {
        return cookieSize;
    }
    public int getPreparationTime() {
        return cookie.getPreparationTime();
    }
    public ArrayList<Ingredient> getIngredients() {
        return cookie.getIngredientsList();
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
