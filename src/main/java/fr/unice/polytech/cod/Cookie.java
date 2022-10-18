package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.Topping;

import java.util.ArrayList;
import java.util.List;

public class Cookie {
    private String name;
    private Dough dough;
    private Flavour flavour;
    private List<Topping> toppingList;

    public Cookie(String cookieName, Dough dough, Flavour flavour, List<Topping> toppingList){
        this.name = cookieName;
        this.dough = dough;
        this.flavour = flavour;
        this.toppingList = toppingList; //TODO: Make sure the given topping list isn't > 3
    }

    public Cookie(String testCookie, Flavour flavour) {
    }

    public List<Ingredient> getIngredients(){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(dough);
        ingredients.add(flavour);
        ingredients.addAll(toppingList);
        return ingredients;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "cookieName='" + name + '\'' +
                ", dough=" + dough +
                ", flavour=" + flavour +
                ", toppingList=" + toppingList +
                '}';
    }
}
