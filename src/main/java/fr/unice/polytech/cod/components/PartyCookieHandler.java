package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.PartyCookie;
import fr.unice.polytech.cod.interfaces.IPartyCookieHandler;
import fr.unice.polytech.cod.interfaces.Saleable;
import fr.unice.polytech.cod.pojo.ingredient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PartyCookieHandler implements IPartyCookieHandler {

    @Autowired
    Saleable saleable;


    @Override
    public void customize(PartyCookie partyCookie, HashMap<Ingredient, Boolean> customIngrdedients) {
        for (Map.Entry<Ingredient, Boolean> set : customIngrdedients.entrySet()) {
            if (set.getValue()) {
                if (set.getKey() instanceof Topping)
                    addTopping(partyCookie, (Topping) set.getKey());
                else if (set.getKey() instanceof Dough)
                    partyCookie.setDough((Dough) set.getKey());
                else if (set.getKey() instanceof Flavour)
                    partyCookie.setFlavour((Flavour) set.getKey());
                else if (set.getKey() instanceof Cooking)
                    partyCookie.setCooking((Cooking) set.getKey());
                else if (set.getKey() instanceof Mix)
                    partyCookie.setMix((Mix) set.getKey());
            } else if (set.getKey() instanceof Topping)
                removeTopping(partyCookie, (Topping) set.getKey());
        }
    }
    private void removeTopping(PartyCookie partyCookie, Topping t) {
        if(saleable.getIngredients(partyCookie).contains(t)){
            saleable.getIngredientsList(partyCookie).remove(t);
        }
    }
    private void addTopping(PartyCookie partyCookie, Topping t) {
        partyCookie.getToppingList().add(t);
    }
}
