package fr.unice.polytech.cod.decorator_pattern.decorator;

import fr.unice.polytech.cod.decorator_pattern.PartyCookieComponent;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Mix;

import java.util.ArrayList;

public class MixDecorator extends Decorator{
    protected Mix mix;
    protected Mix lastMix;


    public MixDecorator(PartyCookieComponent partyCookieComponent, Mix mix) {
        super(partyCookieComponent, DecoratorState.ADD);
        this.mix = mix;
        this.lastMix = (Mix) super.getIngredients().get(getLastMixIndex());
    }

    @Override
    public String getName() {
        return partyCookieComponent.getName().replaceAll("\\b"+this.lastMix.getName()+"\\b", this.mix.getName());
    }

    @Override
    public double getPrice() {
        return partyCookieComponent.getPrice() - this.lastMix.getPrice() + this.mix.getPrice();
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
