package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.pojo.IngredientCatalog;
import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Flavour;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Topping;
import fr.unice.polytech.cod.interfaces.CatalogExplorer;

import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class CatalogComponent implements CatalogExplorer {
    @Override
    public List<Ingredient> getIngredientList(IngredientCatalog ingredientCatalog) {
        return ingredientCatalog.ingredientList;
    }

    @Override
    public boolean isInCatalog(IngredientCatalog ingredientCatalog, Ingredient ingredient) {
        return ingredientCatalog.ingredientList.contains(ingredient);
    }

    @Override
    public Dough getDough(IngredientCatalog ingredientCatalog, String name) {
        for (Ingredient ingredient : ingredientCatalog.ingredientList){
            if(ingredient.getName().equals(name))
                return new Dough(ingredient.getName(),ingredient.getPrice(), ingredient.getQuantity());
        }
        return null;
    }

    @Override
    public Flavour getFlavour(IngredientCatalog ingredientCatalog, String name) {
        for (Ingredient ingredient : ingredientCatalog.ingredientList){
            if(ingredient.getName().equals(name))
                return new Flavour(ingredient.getName(),ingredient.getPrice(), ingredient.getQuantity());
        }
        return null;
    }

    @Override
    public Topping getTopping(IngredientCatalog ingredientCatalog, String name) {
        for (Ingredient ingredient : ingredientCatalog.ingredientList){
            if(ingredient.getName().equals(name))
                return new Topping(ingredient.getName(),ingredient.getPrice(), ingredient.getQuantity());
        }
        return null;
    }
}
