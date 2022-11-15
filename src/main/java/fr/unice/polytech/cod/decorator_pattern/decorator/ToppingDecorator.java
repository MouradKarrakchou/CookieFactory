package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.PartyCookieComponent;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Topping;

import java.util.ArrayList;

public class ToppingDecorator extends Decorator{
    protected Topping topping;

    public ToppingDecorator(PartyCookieComponent partyCookieComponent, DecoratorState decoratorState, Topping topping) {
        super(partyCookieComponent,decoratorState);
        this.topping = topping;
    }

    @Override
    public String getName() {
        if (decoratorState.equals(DecoratorState.ADD))
            return partyCookieComponent.getName()  + " " + topping.getName();
        return partyCookieComponent.getName() + " without " + topping.getName();
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookieComponent.getPrice() + topping.getPrice();
        return partyCookieComponent.getPrice() - topping.getPrice();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(partyCookieComponent.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.add(this.topping);
        else
            allIngredients.remove(this.topping);
        return allIngredients;
    }

}
