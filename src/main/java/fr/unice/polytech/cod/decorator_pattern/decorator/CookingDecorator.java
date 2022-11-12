package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.ingredient.Cooking;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class CookingDecorator extends Decorator{
    protected Cooking cooking;


    public CookingDecorator(CookieComponent cookieComponent, DecoratorState decoratorState) {
        super(cookieComponent, decoratorState);

    }

    @Override
    public String getName() {
        if (decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getName() + " " + cooking.getName();
        return cookieComponent.getName() + " without " + cooking.getName();
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getPrice() + cooking.getPrice();
        return cookieComponent.getPrice() - cooking.getPrice();
    }

    @Override
    public int getPreparationTime() {
        if(decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getPreparationTime() + cooking.getPreparationTime();
        return cookieComponent.getPreparationTime() - cooking.getPreparationTime();

    }

    @Override
    public List<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(cookieComponent.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.add(this.cooking);
        else
            allIngredients.remove(this.cooking);
        return allIngredients;
    }
}
