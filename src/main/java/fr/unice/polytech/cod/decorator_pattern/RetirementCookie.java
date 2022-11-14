package fr.unice.polytech.cod.decorator_pattern;

import fr.unice.polytech.cod.food.ingredient.*;

import java.util.ArrayList;
import java.util.List;

public class RetirementCookie extends CookieComponent{
    public RetirementCookie() {
        super.name = "Retirement cookie";
        super.price = 30;
        super.preparationTime = 120;
        super.cookieSize = CookieSize.XL;
        super.ingredients = generateIngredients();
    }

    @Override
    protected ArrayList<Ingredient> generateIngredients(){
        Dough dought = new Dough("RetirementDough", 3,3);
        Flavour flavour = new Flavour("FruitFlavour", 2, 2);
        Topping topping = new Topping("Fruits", 2, 4);
        Mix mix = new Mix(Mix.MixState.TOPPED);
        Cooking cooking = new Cooking(Cooking.CookingState.CRUNCHY);
        return new ArrayList<>(List.of(dought, flavour,topping, mix, cooking));
    }
}
