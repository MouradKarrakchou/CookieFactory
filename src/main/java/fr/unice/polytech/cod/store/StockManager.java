package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.Set;

public class StockManager {
    private final Stock stock;

    public StockManager(){
        stock = new Stock();
    }

    /**
     * For a given set of ingredients check if there is enough of these ingredients in the stock.
     *
     * @param ingredients - The given set of ingredients to check.
     * @return boolean - If there is enough of these ingredients in the stock.
     */
    public boolean hasEnough(Set<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients)
            if (!stock.hasEnough(ingredient)) return false;
        return true;
    }
}
