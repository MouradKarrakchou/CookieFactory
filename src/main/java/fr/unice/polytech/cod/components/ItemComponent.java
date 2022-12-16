package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.IngredientActions;
import fr.unice.polytech.cod.interfaces.OrderStatesAction;
import fr.unice.polytech.cod.interfaces.ScheduleActions;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.ItemActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ItemComponent implements ItemActions {

    IngredientActions ingredientActions;
    @Autowired
    public ItemComponent(IngredientActions ingredientActions) {
        this.ingredientActions = ingredientActions;
    }

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
        return null;
    }

    @Override
    public Set<Ingredient> generateIngredientsNeeded(List<Item> items) {
        return null; //TODO
    }
}
