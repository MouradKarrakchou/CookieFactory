package fr.unice.polytech.cod.decorator_pattern;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.*;

import java.util.ArrayList;
import java.util.List;

public class PartyCookie extends Cookie {
    public enum CookieSize {
        L , XL, XXL
    }
    protected String name;
    protected double price;
    protected ArrayList<Ingredient> ingredients;
    protected CookieSize cookieSize;

    public PartyCookie(Cookie cookie, CookieSize cookieSize) {
        super(cookie);
        this.cookieSize = cookieSize;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public ArrayList<Ingredient> getPartyCookieIngredients() {
        return ingredients;
    }

    public CookieSize getCookieSize() {
        return cookieSize;
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
