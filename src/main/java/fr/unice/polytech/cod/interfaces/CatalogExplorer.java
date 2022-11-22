package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.data.IngredientCatalog;
import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Flavour;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Topping;

import java.util.List;

public interface CatalogExplorer {
    List<Ingredient> getIngredientList(IngredientCatalog ingredientCatalog);
    boolean isInCatalog(IngredientCatalog ingredientCatalog, Ingredient ingredient);

    Dough getDough(IngredientCatalog ingredientCatalog, String name);
    Flavour getFlavour(IngredientCatalog ingredientCatalog, String name);
    Topping getTopping(IngredientCatalog ingredientCatalog, String name);


}
