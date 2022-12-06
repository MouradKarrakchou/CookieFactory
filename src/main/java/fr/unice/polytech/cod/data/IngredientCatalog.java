package fr.unice.polytech.cod.data;

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

    /**
     * Check if a Ingredient is in the catalog
     *
     * @param ingredient a ingredient
     * @return if the catalog contains the ingredient
     */
    public boolean isInCatalog(Ingredient ingredient){
        return this.ingredientList.contains(ingredient);
    }

    /**
     * Find a Dough with a dough name in the catalog
     *
     * @param doughName a cookie name
     * @return the Dough of the dough name
     */
    public Dough getDough(String doughName) {
        for (Ingredient ingredient : this.ingredientList){
            if(ingredient.getName().equals(doughName))
                return new Dough(ingredient.getName(),ingredient.getPrice(), ingredient.getQuantity());
        }
        return null;
    }

    /**
     * Find a Dough with a flavour name in the catalog
     *
     * @param flavourName a flavour name
     * @return the Dough of the flavour name
     */
    public Flavour getFlavour(String flavourName) {
        for (Ingredient ingredient : this.ingredientList){
            if(ingredient.getName().equals(flavourName))
                return new Flavour(ingredient.getName(),ingredient.getPrice(), ingredient.getQuantity());
        }
        return null;
    }

    /**
     * Find a Topping with a topping name in the catalog
     *
     * @param toppingName a topping name
     * @return the Dough of the topping name
     */
    public Topping getTopping(String toppingName) {
        for (Ingredient ingredient : this.ingredientList){
            if(ingredient.getName().equals(toppingName))
                return new Topping(ingredient.getName(),ingredient.getPrice(), ingredient.getQuantity());
        }
        return null;
    }
}
