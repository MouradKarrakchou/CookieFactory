package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.PartyCookie;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.List;

public abstract class Decorator extends PartyCookie {
    public enum DecoratorState {
        ADD, REMOVE
    }
    PartyCookie partyCookie;
    private String nameDecorator;
    private double priceDecorator;
    private int preparationTimeDecorator;
    private List<Ingredient> ingredientsDecorator;
    protected DecoratorState decoratorState;

    public Decorator(PartyCookie partyCookie, DecoratorState decoratorState) {
        super(null,null);
        this.partyCookie = partyCookie;
        ingredientsDecorator = new ArrayList<>();
        this.decoratorState = decoratorState;
    }

    @Override
    public String getName() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookie.getName() + " " + this.nameDecorator;
        return partyCookie.getName() +" without "+ this.nameDecorator;
    }

    @Override
    public double getPrice() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookie.getPrice() + this.priceDecorator;
        return partyCookie.getPrice() - this.priceDecorator;
    }

    @Override
    public int getPreparationTime() {
        if(decoratorState.equals(DecoratorState.ADD))
            return partyCookie.getPreparationTime() + this.preparationTimeDecorator;
        return partyCookie.getPreparationTime() - this.preparationTimeDecorator;

    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>(partyCookie.getIngredients());
        if(decoratorState.equals(DecoratorState.ADD))
            allIngredients.addAll(this.ingredientsDecorator);
        else
            allIngredients.removeAll(this.ingredientsDecorator);
        return allIngredients;
    }

    @Override
    public CookieSize getCookieSize() {
        return partyCookie.getCookieSize();
    }


}
