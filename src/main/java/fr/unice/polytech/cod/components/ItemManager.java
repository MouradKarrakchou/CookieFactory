package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.IngredientActions;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.ItemActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.Set;

@Component
public class ItemManager implements ItemActions {
    @Autowired
    private IngredientActions ingredientActions;

    @Override
    public void updateQuantity(Item item, int value) {
        item.setQuantity(item.getQuantity() + value);
    }

    @Override
    public Set<Ingredient> generateIngredientsNeeded(Item item) {
        // Get ingredients needed for 1 cookie
        Set<Ingredient> ingredientsClone = new HashSet<>(item.getCookie().getIngredients());

        // Multiplying each ingredient needed by the number of cookies
        for (Ingredient ingredient : ingredientsClone)
            this.ingredientActions.multiplyQuantity(ingredient, item.getQuantity());

        return ingredientsClone;
    }

    @Override
    public Set<Ingredient> generateIngredientsNeeded(Set<Item> items) {
        Set<Ingredient> neededIngredients = new HashSet<>();
        // Check the list of items
        for (Item item : items) {
            // Generating all needed ingredients for each item
            for (Ingredient ingredient : generateIngredientsNeeded(item)) {
                // Merging all needed ingredients together
                boolean isAdded = false;
                for (Ingredient neededIngredient : neededIngredients) {
                    if (neededIngredient.equals(ingredient)) {
                        ingredientActions.increaseQuantity(neededIngredient, ingredient.getQuantity());
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded)
                    neededIngredients.add(ingredient);
            }
        }
        return neededIngredients;
    }
}
