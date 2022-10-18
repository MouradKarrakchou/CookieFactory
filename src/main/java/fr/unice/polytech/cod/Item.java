package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

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
     * Generate a list of needed ingredients according to the item list
     *
     * @return The of needed ingredients
     */
    public List<Ingredient> generateIngredientsNeeded(){
        // Cloning the list of ingredients
        List<Ingredient> ingredientsClone = new ArrayList<>(cookie.getIngredients());

        // Multiplying each ingredient needed by the number of cookies
        for(Ingredient ingredient : ingredientsClone)
            ingredient.multiplyQuantity(quantity);

        return ingredientsClone;
    }
}
