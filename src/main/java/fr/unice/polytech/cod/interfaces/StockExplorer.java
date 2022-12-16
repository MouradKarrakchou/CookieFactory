package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.pojo.Stock;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;


public interface StockExplorer {
    Optional<Ingredient> findIngredient(Stock stock, Ingredient ingredient);
    boolean hasEnoughIngredients(Stock stock, Ingredient ingredient);
    boolean hasEnoughIngredients(Stock stock, Set<Ingredient> ingredients);
}
