package fr.unice.polytech.cod.decorator_pattern;

import fr.unice.polytech.cod.ingredient.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CookieComponent {
    public enum CookieSize {
        L , XL, XXL
    }
    protected String name;
    protected double price;
    protected int preparationTime;
    protected List<Ingredient> ingredients;
    protected CookieSize cookieSize;

    protected List<Ingredient> generateIngredients(){
        Dough dought = new Dough("", 0,0);
        Flavour flavour = new Flavour("", 0, 0);
        Topping topping = new Topping("", 0, 0);
        Mix mix = new Mix(Mix.MixState.MIXED);
        Cooking cooking = new Cooking(Cooking.CookingState.CHEWY);
        return List.of(dought, flavour,topping, mix, cooking);
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getPreparationTime() {
        return preparationTime;
    }
    public List<Ingredient> getIngredients() {
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
                ", ingredients=" + getIngredients() +
                ", cookieSize=" + getCookieSize() +
                '}';
    }
}
