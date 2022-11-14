package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Topping;

import java.util.ArrayList;

public class ToppingDecorator extends Decorator{
    protected Topping topping;

    public ToppingDecorator(CookieComponent cookieComponent, DecoratorState decoratorState, Topping topping) {
        super(cookieComponent,decoratorState);
        this.topping = topping;
    }

    @Override
    public String getName() {
        if (decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getName()  + " " + topping.getName();
        return cookieComponent.getName() + " without " + topping.getName();
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getPrice() + topping.getPrice();
        return cookieComponent.getPrice() - topping.getPrice();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(cookieComponent.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.add(this.topping);
        else
            allIngredients.remove(this.topping);
        return allIngredients;
    }

}
