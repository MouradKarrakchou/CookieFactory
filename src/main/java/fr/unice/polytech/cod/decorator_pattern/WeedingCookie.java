package fr.unice.polytech.cod.decorator_pattern;

import fr.unice.polytech.cod.food.ingredient.*;

import java.util.ArrayList;
import java.util.List;

public class WeedingCookie extends CookieComponent{
    public WeedingCookie() {
        super.name = "Weeding cookie";
        super.price = 50;
        super.preparationTime = 180;
        super.cookieSize = CookieSize.XXL;
        super.ingredients = generateIngredients();
    }

    @Override
    protected ArrayList<Ingredient> generateIngredients(){
        Dough dought = new Dough("WeedingDough", 3,3);
        Flavour flavour = new Flavour("RosesFlavour", 2, 2);
        Topping topping = new Topping("Roses", 2, 4);
        Mix mix = new Mix(Mix.MixState.MIXED);
        Cooking cooking = new Cooking(Cooking.CookingState.CHEWY);
        return new ArrayList<>(List.of(dought, flavour,topping, mix, cooking));
    }
}
