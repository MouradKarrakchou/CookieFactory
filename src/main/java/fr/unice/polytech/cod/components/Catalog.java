package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.data.IngredientCatalog;
import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Flavour;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Topping;
import fr.unice.polytech.cod.interfaces.CatalogExplorer;

import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class Catalog implements CatalogExplorer {
    @Override
    public List<Ingredient> getIngredientList(IngredientCatalog ingredientCatalog) {
        return null; //TODO
    }

    @Override
    public boolean isInCatalog(IngredientCatalog ingredientCatalog, Ingredient ingredient) {
        return false; //TODO
    }

    @Override
    public Dough getDough(IngredientCatalog ingredientCatalog, String name) {
        return null; //TODO
    }

    @Override
    public Flavour getFlavour(IngredientCatalog ingredientCatalog, String name) {
        return null; //TODO
    }

    @Override
    public Topping getTopping(IngredientCatalog ingredientCatalog, String name) {
        return null; //TODO
    }
}
