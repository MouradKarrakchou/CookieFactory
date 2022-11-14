package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.food.ingredient.Flavour;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import java.util.ArrayList;

public class FlavourDecorator extends Decorator{

    protected Flavour flavour;


    public FlavourDecorator(CookieComponent cookieComponent, Flavour flavour, DecoratorState decoratorState) {
        super(cookieComponent, decoratorState);
        this.flavour = flavour;
    }

    @Override
    public String getName() {
        if (decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getName() + " " + flavour.getName();
        return cookieComponent.getName() + " without " + flavour.getName();
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getPrice() + flavour.getPrice();
        return cookieComponent.getPrice() - flavour.getPrice();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(cookieComponent.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.add(this.flavour);
        else
            allIngredients.remove(this.flavour);
        return allIngredients;
    }
}
