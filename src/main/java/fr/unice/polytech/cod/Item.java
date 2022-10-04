package fr.unice.polytech.cod;

import fr.unice.polytech.cod.Recipe;

import java.util.Queue;

public class Item {
    private Recipe recipe;
    private int quantity;


    public Item(Recipe recipe, int quantity){
        this.quantity = quantity;
        this.recipe = recipe;
    }
}
