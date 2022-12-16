package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.IngredientCatalog;
import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Flavour;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Topping;

import java.util.List;

public interface CatalogExplorer {
    /**
     * Check if a Ingredient is in the catalog.
     *
     * @param ingredientCatalog - The ingredient catalog.
     * @param ingredient -  A ingredient.
     * @return
     */
    boolean isInCatalog(IngredientCatalog ingredientCatalog, Ingredient ingredient);

    /**
     *  Find a Dough with a dough name in the catalog.
     *
     * @param ingredientCatalog - The ingredient catalog.
     * @param name - A dough name.
     * @return the Dough of the dough name.
     */
    Dough getDough(IngredientCatalog ingredientCatalog, String name);

    /**
     * Find a Dough with a flavour name in the catalog.
     *
     * @param ingredientCatalog - The ingredient catalog.
     * @param name - A flavour name.
     * @return the new Flavour of the flavour name.
     */
    Flavour getFlavour(IngredientCatalog ingredientCatalog, String name);


    /**
     * Find a Topping with a topping name in the catalog.
     *
     * @param ingredientCatalog - The ingredient catalog.
     * @param name - A topping name.
     * @return the new Topping of the topping name.
     */
    Topping getTopping(IngredientCatalog ingredientCatalog, String name);

}