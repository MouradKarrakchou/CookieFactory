package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.pojo.IngredientCatalog;
import fr.unice.polytech.cod.pojo.ingredient.Dough;
import fr.unice.polytech.cod.pojo.ingredient.Flavour;
import fr.unice.polytech.cod.pojo.ingredient.Ingredient;
import fr.unice.polytech.cod.pojo.ingredient.Topping;
import fr.unice.polytech.cod.interfaces.ICatalogExplorer;

import org.springframework.stereotype.Component;


@Component
public class CatalogExplorer implements ICatalogExplorer {
    @Override
    public boolean isInCatalog(Ingredient ingredient) {
        return IngredientCatalog.instance.ingredientList.contains(ingredient.clone());
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
