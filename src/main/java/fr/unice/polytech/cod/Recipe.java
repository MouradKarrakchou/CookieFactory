package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Topping;

import java.util.List;

public class Recipe {
    private String recipeName;
    private Dough dough;
    private Flavour flavour;
    private List<Topping> toppingList;

    public Recipe (String recipeName, Dough dough, Flavour flavour, List<Topping> toppingList){
        this.recipeName = recipeName;
        this.dough = dough;
        this.flavour = flavour;
        this.toppingList = toppingList; //TODO: Make sure the given topping list isn't > 3
    }

}
