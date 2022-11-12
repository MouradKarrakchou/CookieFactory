package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.Mix;
import fr.unice.polytech.cod.ingredient.Topping;

import java.util.ArrayList;
import java.util.List;

public class MixDecorator extends Decorator{
    protected Mix mix;
    protected Mix lastMix;


    public MixDecorator(CookieComponent cookieComponent, Mix mix) {
        super(cookieComponent, DecoratorState.ADD);
        this.mix = mix;
        this.lastMix = (Mix) super.getIngredients().get(getLastMixIndex());
    }

    @Override
    public String getName() {
        return cookieComponent.getName().replaceAll("\\b"+this.lastMix.getName()+"\\b", this.mix.getName());
    }

    @Override
    public double getPrice() {
        return cookieComponent.getPrice() - this.lastMix.getPrice() + this.mix.getPrice();
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        ArrayList<Ingredient> allIngredients = super.getIngredients();
        allIngredients.set(getLastMixIndex(), mix);
        return allIngredients;
    }

    public int getLastMixIndex() {
        ArrayList<Ingredient> allIngredients = super.getIngredients();
        for(int i = 0 ; i < allIngredients.size() ; i++){
            if(allIngredients.get(i) instanceof Mix){
                return i;
            }
        }
        return -1;
    }

}
