package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.HashSet;
import java.util.Set;

public class Item {
    private Cookie cookie;
    private int quantity;


    public Item(Cookie cookie, int quantity) {
        this.quantity = quantity;
        this.cookie = cookie;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Generate a set of all needed ingredients according to the cookie on the list and its quantity.
     *
     * @return The number of needed ingredients
     */
    public Set<Ingredient> getIngredientsNeeded(){
        // Cloning the list of ingredients
        Set<Ingredient> ingredientsClone = new HashSet<>(cookie.getIngredients());

        // Multiplying each ingredient needed by the number of cookies
        for(Ingredient ingredient : ingredientsClone)
            ingredient.multiplyQuantity(quantity);

        return ingredientsClone;
    }
}
