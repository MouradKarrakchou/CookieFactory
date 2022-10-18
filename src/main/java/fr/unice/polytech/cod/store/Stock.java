package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private List<Ingredient> ingredients;

    public Stock(){
        ingredients = new ArrayList<>();
    }
}
