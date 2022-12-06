package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.StockExplorer;
import fr.unice.polytech.cod.interfaces.StockModifier;
import fr.unice.polytech.cod.store.Stock;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class StockComponent implements StockExplorer, StockModifier {

    @Override
    public Optional<Ingredient> findIngredient(Stock stock, Ingredient ingredient) {
        return stock.ingredients.stream().filter(i -> i.equals(ingredient)).findFirst();
    }

    /**
     * Check if the stock has enough of the given ingredient
     *
     * @param stock The stock to check the ingredient
     * @param ingredient the ingredient to check
     * @return If the stock has enough ingredient
     */
    @Override
    public boolean hasEnoughIngredient(Stock stock, Ingredient ingredient) {
        Optional<Ingredient> optionalIngredient = findIngredient(stock, ingredient);
        if (optionalIngredient.isEmpty())
            return false;
        return optionalIngredient.get().getQuantity() >= ingredient.getQuantity();
    }

    /**
     * Check if the stock has enough of the given ingredients
     *
     * @param stock The stock to check the ingredients
     * @param ingredients the ingredients to check
     * @return If the stock has enough ingredients
     */
    @Override
    public boolean hasEnoughIngredients(Stock stock, Set<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients)
            if (!stock.hasEnough(ingredient)) return false;
        return true;
    }

    /**
     * Lock an ingredient
     * @param stock The stock to lock the ingredient
     * @param ingredient The ingredient to lock
     * @return If the ingredient has been locked
     */
    @Override
    public boolean lock(Stock stock, Ingredient ingredient) {
        Optional<Ingredient> _stockIngredient = findIngredient(stock, ingredient);

        // If the ingredient needed isn't in the stock, we can't lock it.
        if (_stockIngredient.isEmpty())
            return false;

        // Opening the Optional<Ingredient>
        Ingredient stockIngredient = _stockIngredient.get();

        // If there is not enough quantity on the stock to be locked.
        if (ingredient.getQuantity() > stockIngredient.getQuantity())
            return false;

        // Removing quantity from stock ingredient & add this quantity to locked ingredient
        stockIngredient.quantity -= ingredient.getQuantity();
        addToLockedIngredients(stock, ingredient);

        return true;
    }

    /**
     * Add an ingredient
     * @param stock The stock to add the ingredient
     * @param ingredient The ingredient to add
     */
    @Override
    public void addIngredient(Stock stock, Ingredient ingredient) {
        Optional<Ingredient> optionalIngredient = findIngredient(stock, ingredient);

        // If the ingredient isn't in the stock, we add it.
        if (optionalIngredient.isEmpty())
            stock.ingredients.add(ingredient.clone());

        // If the ingredient is in the stock, we increase the quantity of it.
        else
            optionalIngredient.get().increaseQuantity(ingredient.getQuantity());
    }

    /**
     * Add ingredients
     * @param stock The stock to add the ingredient
     * @param ingredients The set of ingredients to add
     */
    @Override
    public void addIngredients(Stock stock, List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients)
            addIngredient(stock, ingredient);
    }

    /**
     * Add an ingredient to the locked stock
     * @param stock The stock to lock the ingredient
     * @param ingredient If the ingredient has been locked
     */
    private void addToLockedIngredients(Stock stock, Ingredient ingredient) {
        Optional<Ingredient> _lockedIngredient = stock.lockedIngredients.stream()
                .filter(i -> i.equals(ingredient)).findFirst();

        // If the ingredient isn't in the locked stock, we add it.
        if (_lockedIngredient.isEmpty()) {
            stock.lockedIngredients.add(ingredient.clone());
        }

        // If the ingredient is in the locked stock, we increase the quantity of it.
        else
            _lockedIngredient.get().increaseQuantity(ingredient.getQuantity());
    }
}
