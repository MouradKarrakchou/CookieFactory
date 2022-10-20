package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.NotEnoughQuantityException;

import java.util.*;

public class Stock {
    private Set<Ingredient> ingredients;
    private Set<Ingredient> lockedIngredients;

    public Stock(){
        ingredients = new HashSet<>();
        lockedIngredients = new HashSet<>();
    }

    /**
     * Lock a given ingredient to the store.
     *
     * @param ingredient - The ingredient to lock.
     * @return boolean - If the ingredient has been locked.
     */
    public boolean lock(Ingredient ingredient){
        Optional<Ingredient> _stockIngredient = ingredients.stream().filter(i -> i.equals(ingredient)).findFirst();

        // If the ingredient needed isn't in the stock, we can't lock it.
        if(_stockIngredient.isEmpty())
            return false;

        // Opening the Optional<Ingredient>
        Ingredient stockIngredient = _stockIngredient.get();
        Ingredient toLockIngredient;
        try {
            toLockIngredient = stockIngredient.split(ingredient.getQuantity());
        }
        // If the ingredient quantity in the stock isn't enough, we can't lock it.
        catch (NotEnoughQuantityException e) {
            return false;
        }

        addToLockedIngredients(toLockIngredient);
        return true;
    }

    private void addToLockedIngredients(Ingredient ingredient){
        Optional<Ingredient> _lockedIngredient = ingredients.stream().filter(i -> i.equals(ingredient)).findFirst();

        // If the ingredient isn't in the stock, we add it.
        if(_lockedIngredient.isEmpty())
            lockedIngredients.add(ingredient);

        // If the ingredient is in the stock, we increase the quantity of it.
        else
            _lockedIngredient.get().increaseQuantity(ingredient.getQuantity());
    }
}
