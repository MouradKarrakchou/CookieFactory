package fr.unice.polytech.cod.pojo;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import java.io.Serializable;
import java.util.*;

/**
 * Pojo class : Stock
 */
public class Stock implements Serializable {
    private final Set<Ingredient> ingredients;
    private final Set<Ingredient> lockedIngredients;

    public Stock() {
        ingredients = new HashSet<>();
        lockedIngredients = new HashSet<>();
    }

    public Set<Ingredient> getLockedIngredients(){
        return lockedIngredients;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

}
