package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.Topping;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cookie {
    private String name;
    private Dough dough;
    private Flavour flavour;
    private List<Topping> toppingList; // TODO : Maybe topping should be represented as a set
    private int preparationTime; // en minutes

    public Cookie(String cookieName, Dough dough, Flavour flavour, List<Topping> toppingList, int preparationTime){
        this.name = cookieName;
        this.dough = dough;
        this.flavour = flavour;
        this.toppingList = toppingList; //TODO: Make sure the given topping list isn't > 3
        this.preparationTime = preparationTime;
    }
    /**
     * Give the set of all ingredients needed to produce one cookie base on its recipe.
     *
     * @return The set of all ingredients needed.
     */
    public Set<Ingredient> getIngredients(){
        Set<Ingredient> ingredients = new HashSet<>();

        if(dough != null) ingredients.add(dough);
        if(flavour != null) ingredients.add(flavour);
        ingredients.addAll(toppingList);
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public Dough getDough() {
        return dough;
    }

    public Flavour getFlavour() {
        return flavour;
    }

    public List<Topping> getToppingList() {
        return toppingList;
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

    public int getPreparationTime() {
        return preparationTime;
    }
}
