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
                List.of(new Dough("pate", 0.2, 1),
                        new Dough("plain", 0.2, 1),
                        new Dough("peanut butter", 0.2, 1),
                        new Dough("oatmeal", 0.2, 1),

                        new Flavour("chocolate", 0.2, 1),
                        new Flavour("vanilla", 0.2, 1),
                        new Flavour("cinnamon", 0.2, 1),
                        new Flavour("chili", 0.2, 1),

                        new Topping("white chocolate", 0.2, 1),
                        new Topping("milk chocolate", 0.2, 1),
                        new Topping("M&M’s", 0.2, 1),
                        new Topping("Reese’s buttercup", 0.2, 1),
                        new Topping("chocolate chips", 0.15, 1),
                        new Topping("cream", 0.12, 1)
                ));
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

}