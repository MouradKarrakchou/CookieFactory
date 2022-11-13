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
        List<Dough> doughList = new ArrayList<>();
        for (int i = 0; i < this.ingredientList.size(); i ++){
            if(this.ingredientList.get(i) instanceof Dough){
                doughList.add((Dough) this.ingredientList.get(i));
            }
        }
        return doughList;
    }

    public List<Flavour> getFlavourList() {
        List<Flavour> flavourList = new ArrayList<>();
        for (int i = 0; i < this.ingredientList.size(); i ++){
            if(this.ingredientList.get(i) instanceof Flavour){
                flavourList.add((Flavour) this.ingredientList.get(i));
            }
        }
        return flavourList;
    }

    public List<Topping> getToppingList() {
        List<Topping> toppingList = new ArrayList<>();
        for (int i = 0; i < this.ingredientList.size(); i ++){
            if(this.ingredientList.get(i) instanceof Topping){
                toppingList.add((Topping) this.ingredientList.get(i));
            }
        }
        return toppingList;
    }
}
