package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.store.Stock;

import java.util.List;

public interface StockModifier {
    boolean lock(Stock stock, Ingredient ingredient);
    void addIngredient(Stock stock, Ingredient ingredient);
    void addIngredients(Stock stock, List<Ingredient> ingredients);
}