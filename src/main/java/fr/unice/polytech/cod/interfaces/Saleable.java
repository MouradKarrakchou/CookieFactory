package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.pojo.ingredient.Ingredient;
import fr.unice.polytech.cod.pojo.store.Store;

import java.util.Set;

public interface Saleable {
    double getPrice(Store store, Cookie cookie);

}
