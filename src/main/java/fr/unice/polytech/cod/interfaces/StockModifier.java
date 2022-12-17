package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.ingredient.Ingredient;
import fr.unice.polytech.cod.pojo.Stock;

import java.util.List;

public interface StockModifier {

    boolean lock(Stock stock, Ingredient ingredient);

    void addIngredient(Stock stock, Ingredient ingredient);

    void addIngredients(Stock stock, List<Ingredient> ingredients);
}
