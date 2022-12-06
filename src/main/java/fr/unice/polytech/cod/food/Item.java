package fr.unice.polytech.cod.food;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Item {
    private final Cookie cookie;
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
     * Increase the quantity by a given value
     * @param value The value to increase the quantity
     */
    public void updateQuantity(int value){
        quantity += value;
    }

    /**
     * Generate a set of all needed ingredients according to the cookie on the list and its quantity.
     * @return The number of needed ingredients
     */
    public Set<Ingredient> generateIngredientsNeeded(){
        // Cloning the list of ingredients
        cookie.getIngredients();
        Set<Ingredient> ingredientsClone = new HashSet<>(cookie.getIngredientsCloned());

        // Multiplying each ingredient needed by the number of cookies
        for(Ingredient ingredient : ingredientsClone)
            ingredient.multiplyQuantity(quantity);

        return ingredientsClone;
    }

    /**
     * Return a set of the ingredients of the item
     * @return A set of the ingredients
     */
    public Set<Ingredient> generateIngredients(){
        // Cloning the list of ingredients
        return new HashSet<>(cookie.getIngredients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cookie.name);
    }

    @Override
    public boolean equals(Object object){
        // Check for the address
        if (object == this)
            return true;

        // Check for the instance
        if (!(object instanceof Item i))
            return false;

        // Check for the name equality
        return i.cookie.name.equals(cookie.name);
    }

}
