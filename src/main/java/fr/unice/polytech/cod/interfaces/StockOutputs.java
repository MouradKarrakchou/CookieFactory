package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.store.Stock;

import java.util.Optional;
import java.util.Set;

public interface StockOutputs {
    Optional<Ingredient> findIngredient(Stock stock, Ingredient ingredient);
    boolean hasEnoughIngredient(Stock stock, Ingredient ingredient);
    boolean hasEnoughIngredients(Stock stock, Set<Ingredient> ingredients);
    Set<Ingredient> getIngredients(Stock stock);
}
