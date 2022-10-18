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

    public List<Ingredient> getIngredientsNeeded(){
        List<Ingredient> ingredientsClone = new ArrayList<>(cookie.getIngredients());

        for(Ingredient ingredient : ingredientsClone);
        return ingredientsClone;
    }
}
