package fr.unice.polytech.cod.pojo;

import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Flavour;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Topping;

import java.util.ArrayList;
import java.util.List;

public class IngredientCatalog {
    public final List<Ingredient> ingredientList;
    public static IngredientCatalog instance = new IngredientCatalog();

    private IngredientCatalog() {
        this.ingredientList = new ArrayList<>(
                List.of(new Dough("plain", 0.002, 1),
                        new Dough("chocolate", 0.002, 1),
                        new Dough("peanut butter", 0.002, 1),
                        new Dough("oatmeal", 0.002, 1),

                        new Flavour("vanilla", 0.002, 1),
                        new Flavour("cinnamon", 0.002, 1),
                        new Flavour("chili", 0.002, 1),

                        new Topping("white chocolate", 0.002, 1),
                        new Topping("milk chocolate", 0.002, 1),
                        new Topping("M&M’s", 0.002, 1),
                        new Topping("Reese’s buttercup", 0.002, 1)
                ));
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

}