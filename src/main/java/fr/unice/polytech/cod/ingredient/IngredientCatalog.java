package fr.unice.polytech.cod.ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientCatalog {
    private List<Ingredient> ingredientList;

    public IngredientCatalog() {
        this.ingredientList = new ArrayList<>(
                List.of(new Dough("plain", 0.002, 1),
                        new Dough("chocolate", 0.002, 1),
                        new Dough("peanut butter", 0.002, 1),
                        new Dough("oatmeal", 0.002, 1),
                        new Dough("chocolate", 0.002, 1),

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

    public List<Dough> getDoughList() {
        return (List<Dough>) this.ingredientList.stream().filter(ingre -> ingre instanceof Dough);
    }

    public List<Flavour> getFlavourList() {
        return (List<Flavour>) this.ingredientList.stream().filter(ingre -> ingre instanceof Flavour);
    }

    public List<Topping> getToppingList() {
        return (List<Topping>) this.ingredientList.stream().filter(ingre -> ingre instanceof Topping);
    }
}
