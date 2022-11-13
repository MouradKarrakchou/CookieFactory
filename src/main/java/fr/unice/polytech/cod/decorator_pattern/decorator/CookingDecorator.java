package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.ingredient.Cooking;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.Mix;

import java.util.ArrayList;
import java.util.List;

public class CookingDecorator extends Decorator{
    protected Cooking cooking;
    protected Cooking lastCooking;


    public CookingDecorator(CookieComponent cookieComponent, Cooking cooking) {
        super(cookieComponent, DecoratorState.ADD);
        this.cooking = cooking;
        this.lastCooking = (Cooking) super.getIngredients().get(getLastCookingIndex());
    }

    @Override
    public String getName() {
        return cookieComponent.getName().replaceAll("\\b"+this.lastCooking.getName()+"\\b", this.cooking.getName());
    }

    @Override
    public double getPrice() {
        return cookieComponent.getPrice() - this.lastCooking.getPrice() + this.cooking.getPrice();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = super.getIngredients();
        allIngredients.set(getLastCookingIndex(), cooking);
        return allIngredients;
    }

    public int getLastCookingIndex() {
        ArrayList<Ingredient> allIngredients = super.getIngredients();
        for(int i = 0 ; i < allIngredients.size() ; i++){
            if(allIngredients.get(i) instanceof Cooking){
                return i;
            }
        }
        return -1;
    }
}
