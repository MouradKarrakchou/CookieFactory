package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.ItemActions;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ItemComponent implements ItemActions {
    /**
     * Increase the quantity by a given value
     * @param value The value to increase the quantity
     */
    @Override
    public void updateQuantity(Item item, int value) {
        item.setQuantity(item.getQuantity() + value);
    }

    /**
     * Generate a set of all needed ingredients according to the cookie on the list and its quantity.
     * @return The set of needed ingredients
     */
    @Override
    public Set<Ingredient> generateIngredientsNeeded(Item item) {
        // Get ingredients needed for 1 cookie
        Set<Ingredient> ingredientsClone = new HashSet<>(item.getCookie().getIngredients());

        // Multiplying each ingredient needed by the number of cookies
        for(Ingredient ingredient : ingredientsClone)
            ingredient.multiplyQuantity(item.getQuantity());

        return ingredientsClone;
    }

    @Override
    public Set<Ingredient> generateIngredientsNeeded(Set<Item> items) {
        return null;
    }

    @Override
    public Set<Ingredient> generateIngredientsNeeded(List<Item> items) {
        return null; //TODO
    }
}
