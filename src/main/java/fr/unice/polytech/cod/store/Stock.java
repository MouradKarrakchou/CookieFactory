package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.NotEnoughQuantityException;

import java.util.*;

public class Stock {
    private final Set<Ingredient> ingredients;
    private final Set<Ingredient> lockedIngredients;

    public Stock() {
        ingredients = new HashSet<>();
        lockedIngredients = new HashSet<>();
    }

    /**
     * Lock a given ingredient to the store.
     *
     * @param ingredient - The ingredient to lock.
     * @return boolean - If the ingredient has been locked.
     */
    public boolean lock(Ingredient ingredient) {
        Optional<Ingredient> _stockIngredient = findIngredientInStock(ingredient);

        // If the ingredient needed isn't in the stock, we can't lock it.
        if (_stockIngredient.isEmpty())
            return false;

        // Opening the Optional<Ingredient>
        Ingredient stockIngredient = _stockIngredient.get();

        // If there is not enough quantity on the stock to be locked.
        if (ingredient.getQuantity() > stockIngredient.getQuantity())
            return false;

        moveToLockedIngredients(ingredient);
        return true;
    }

    public Optional<Ingredient> findIngredientInStock(Ingredient ingredient) {
        return ingredients.stream().filter(i -> i.equals(ingredient)).findFirst();
    }

    private void moveToLockedIngredients(Ingredient ingredient) {
        Optional<Ingredient> _lockedIngredient = lockedIngredients.stream().filter(i -> i.equals(ingredient)).findFirst();

        // If the ingredient isn't in the stock, we add it.
        if (_lockedIngredient.isEmpty()){
            lockedIngredients.add(ingredient);
        }

            // If the ingredient is in the stock, we increase the quantity of it.
        else
            _lockedIngredient.get().increaseQuantity(ingredient.getQuantity());
        findIngredientInStock(ingredient).get().increaseQuantity(-ingredient.getQuantity());
    }

    /**
     * Add the given ingredient to the stock.
     *
     * @param ingredient - The ingredient to add in the stock.
     */
    public void addStock(Ingredient ingredient) {
        Optional<Ingredient> _ingredient = findIngredientInStock(ingredient);

        // If the ingredient isn't in the stock, we add it.
        if (_ingredient.isEmpty())
            ingredients.add(ingredient);

            // If the ingredient is in the stock, we increase the quantity of it.
        else
            _ingredient.get().increaseQuantity(ingredient.getQuantity());
    }

    /**
     * For a given ingredient check on the stock if there is enough of this ingredient.
     *
     * @param ingredient - The given ingredient to check on the stock.
     * @return boolean - If there is enough of this ingredient on the stock.
     */
    public boolean hasEnough(Ingredient ingredient) {
        Optional<Ingredient> _ingredient = findIngredientInStock(ingredient);
        if (_ingredient.isEmpty())
            return false;
        return _ingredient.get().getQuantity() >= ingredient.getQuantity();
    }
}
