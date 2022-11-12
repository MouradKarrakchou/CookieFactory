package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public abstract class FlavourDecorator extends Decorator{

    protected Flavour flavour;


    public FlavourDecorator(CookieComponent cookieComponent, DecoratorState decoratorState) {
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
    public List<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(cookieComponent.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.add(this.flavour);
        else
            allIngredients.remove(this.flavour);
        return allIngredients;
    }
}