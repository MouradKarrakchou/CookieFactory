package fr.unice.polytech.cod.decorator_pattern;

import fr.unice.polytech.cod.ingredient.*;

import java.util.ArrayList;
import java.util.List;

public class BirthdayCookie extends CookieComponent{
    public BirthdayCookie() {
        super.name = "Birthday cookie";
        super.price = 25;
        super.preparationTime = 120;
        super.cookieSize = CookieSize.L;
        super.ingredients = generateIngredients();
    }

    @Override
    protected List<Ingredient> generateIngredients(){
        Dough dought = new Dough("BirtdayDough", 3,3);
        Flavour flavour = new Flavour("CandyFlavour", 2, 2);
        Topping topping = new Topping("Candy", 2, 4);
        Mix mix = new Mix(Mix.MixState.MIXED);
        Cooking cooking = new Cooking(Cooking.CookingState.CHEWY);
        return List.of(dought, flavour,topping, mix, cooking);
    }
}
