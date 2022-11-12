package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.Mix;
import fr.unice.polytech.cod.ingredient.Topping;

import java.util.ArrayList;
import java.util.List;

public class MixDecorator extends Decorator{
    protected Mix mix;

    public MixDecorator(CookieComponent cookieComponent, DecoratorState decoratorState) {
        super(cookieComponent, decoratorState);
    }

    @Override
    public String getName() {
        if (decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getName()  + " " + mix.getName();
        return cookieComponent.getName() + " without " + mix.getName();
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getPrice() + mix.getPrice();
        return cookieComponent.getPrice() - mix.getPrice();
    }

    @Override
    public List<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(cookieComponent.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.add(this.mix);
        else
            allIngredients.remove(this.mix);
        return allIngredients;
    }

}
