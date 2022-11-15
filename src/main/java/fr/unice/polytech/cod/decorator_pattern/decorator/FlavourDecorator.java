package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.PartyCookieComponent;
import fr.unice.polytech.cod.food.ingredient.Flavour;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import java.util.ArrayList;

public class FlavourDecorator extends Decorator{

    protected Flavour flavour;


    public FlavourDecorator(PartyCookieComponent partyCookieComponent, Flavour flavour, DecoratorState decoratorState) {
        super(partyCookieComponent, decoratorState);
        this.flavour = flavour;
    }

    @Override
    public String getName() {
        if (decoratorState.equals(DecoratorState.ADD))
            return partyCookieComponent.getName() + " " + flavour.getName();
        return partyCookieComponent.getName() + " without " + flavour.getName();
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookieComponent.getPrice() + flavour.getPrice();
        return partyCookieComponent.getPrice() - flavour.getPrice();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(partyCookieComponent.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.add(this.flavour);
        else
            allIngredients.remove(this.flavour);
        return allIngredients;
    }
}
