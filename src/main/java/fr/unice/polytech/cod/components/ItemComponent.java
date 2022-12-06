package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.ItemActions;

import java.util.List;
import java.util.Set;

public class ItemComponent implements ItemActions {
    @Override
    public void updateQuantity(Item item, int value) {
        //TODO
    }

    @Override
    public int getQuantity(Item item) {
        return 0; //TODO
    }

    @Override
    public Cookie getCookie(Item item) {
        return null; //TODO
    }

    @Override
    public Set<Ingredient> generateIngredientsNeeded(Item item) {
        return null; //TODO
    }

    @Override
    public Set<Ingredient> generateIngredientsNeeded(List<Item> items) {
        return null; //TODO
    }
}
