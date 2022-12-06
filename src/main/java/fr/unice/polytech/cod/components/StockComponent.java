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
        return Optional.empty(); //TODO
    }

    @Override
    public boolean hasEnoughIngredient(Stock stock, Ingredient ingredient) {
        return false; //TODO
    }

    @Override
    public boolean hasEnoughIngredients(Stock stock, Set<Ingredient> ingredients) {
        return false; //TODO
    }

    @Override
    public Set<Ingredient> getIngredients(Stock stock) {
        return null; //TODO
    }

    @Override
    public boolean lock(Stock stock, Ingredient ingredient) {
        return false; //TODO
    }

    @Override
    public void addIngredient(Stock stock, Ingredient ingredient) {
        //TODO
    }

    @Override
    public void addIngredients(Stock stock, List<Ingredient> ingredients) {
        //TODO
    }
}
