package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public abstract class Decorator extends CookieComponent{
    public enum DecoratorState {
        ADD, REMOVE
    }
    CookieComponent cookieComponent;
    private String nameDecorator;
    private double priceDecorator;
    private int preparationTimeDecorator;
    private List<Ingredient> ingredientsDecorator;
    protected DecoratorState decoratorState;

    public Decorator(CookieComponent cookieComponent, DecoratorState decoratorState) {
        this.cookieComponent = cookieComponent;
        ingredientsDecorator = new ArrayList<>();
        this.decoratorState = decoratorState;
    }

    @Override
    public String getName() {
        if(decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getName() + " " + this.nameDecorator;
        return cookieComponent.getName() +" without "+ this.nameDecorator;
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getPrice() + this.priceDecorator;
        return cookieComponent.getPrice() - this.priceDecorator;
    }

    @Override
    public int getPreparationTime() {
        if(decoratorState.equals(DecoratorState.ADD))
            return cookieComponent.getPreparationTime() + this.preparationTimeDecorator;
        return cookieComponent.getPreparationTime() - this.preparationTimeDecorator;

    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(cookieComponent.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.addAll(this.ingredientsDecorator);
        else
            allIngredients.removeAll(this.ingredientsDecorator);
        return allIngredients;
    }

    @Override
    public CookieSize getCookieSize() {
        return cookieComponent.getCookieSize();
    }


}
