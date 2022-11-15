package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.PartyCookie;
import fr.unice.polytech.cod.food.ingredient.Flavour;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import java.util.ArrayList;

public class FlavourDecorator extends Decorator{

    protected Flavour flavour;


    public FlavourDecorator(PartyCookie partyCookie, Flavour flavour, DecoratorState decoratorState) {
        super(partyCookie, decoratorState);
        this.flavour = flavour;
    }

    @Override
    public String getName() {
        if (decoratorState.equals(DecoratorState.ADD))
            return partyCookie.getName() + " " + flavour.getName();
        return partyCookie.getName() + " without " + flavour.getName();
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookie.getPrice() + flavour.getPrice();
        return partyCookie.getPrice() - flavour.getPrice();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(partyCookie.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.add(this.flavour);
        else
            allIngredients.remove(this.flavour);
        return allIngredients;
    }
}
