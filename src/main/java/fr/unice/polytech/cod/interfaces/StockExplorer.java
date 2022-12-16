package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.pojo.Stock;

import java.util.Optional;
import java.util.Set;


public interface StockExplorer {
    Optional<Ingredient> findIngredient(Stock stock, String ingredient);
    Optional<Ingredient> findIngredient(Stock stock, Ingredient ingredient);
    boolean hasEnoughIngredients(Stock stock, Ingredient ingredient);
    boolean hasEnoughIngredients(Stock stock, Set<Ingredient> ingredients);
}
