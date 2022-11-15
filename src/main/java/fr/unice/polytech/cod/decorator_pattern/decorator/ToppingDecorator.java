package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.PartyCookie;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Topping;

import java.util.ArrayList;

public class ToppingDecorator extends Decorator{
    protected Topping topping;

    public ToppingDecorator(PartyCookie partyCookie, DecoratorState decoratorState, Topping topping) {
        super(partyCookie,decoratorState);
        this.topping = topping;
    }

    @Override
    public String getName() {
        if (decoratorState.equals(DecoratorState.ADD))
            return partyCookie.getName()  + " " + topping.getName();
        return partyCookie.getName() + " without " + topping.getName();
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookie.getPrice() + topping.getPrice();
        return partyCookie.getPrice() - topping.getPrice();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(partyCookie.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.add(this.topping);
        else
            allIngredients.remove(this.topping);
        return allIngredients;
    }

}
