package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Item;
import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.List;

public class Store {
    private final Stock stock;

    public Store() {
        stock = new Stock();
    }

    public boolean hasEnoughIngredients(List<Item> items){
        List<Ingredient> ingredientsNeeded;

        for(Item item : items){

        }

        return true;
    }
}
