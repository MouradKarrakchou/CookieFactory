package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.PartyCookieComponent;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public abstract class Decorator extends PartyCookieComponent {
    public enum DecoratorState {
        ADD, REMOVE
    }
    PartyCookieComponent partyCookieComponent;
    private String nameDecorator;
    private double priceDecorator;
    private int preparationTimeDecorator;
    private List<Ingredient> ingredientsDecorator;
    protected DecoratorState decoratorState;

    public Decorator(PartyCookieComponent partyCookieComponent, DecoratorState decoratorState) {
        this.partyCookieComponent = partyCookieComponent;
        ingredientsDecorator = new ArrayList<>();
        this.decoratorState = decoratorState;
    }

    @Override
    public String getName() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookieComponent.getName() + " " + this.nameDecorator;
        return partyCookieComponent.getName() +" without "+ this.nameDecorator;
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookieComponent.getPrice() + this.priceDecorator;
        return partyCookieComponent.getPrice() - this.priceDecorator;
    }

    @Override
    public int getPreparationTime() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookieComponent.getPreparationTime() + this.preparationTimeDecorator;
        return partyCookieComponent.getPreparationTime() - this.preparationTimeDecorator;

    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(partyCookieComponent.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.addAll(this.ingredientsDecorator);
        else
            allIngredients.removeAll(this.ingredientsDecorator);
        return allIngredients;
    }

    @Override
    public CookieSize getCookieSize() {
        return partyCookieComponent.getCookieSize();
    }


}
