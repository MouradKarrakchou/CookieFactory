package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.PartyCookie;
import fr.unice.polytech.cod.interfaces.IPartyCookieHandler;
import fr.unice.polytech.cod.pojo.ingredient.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PartyCookieHandler implements IPartyCookieHandler {
    @Override
    public void addTopping(PartyCookie partyCookie, Topping t) {
        partyCookie.getToppingList().add(t);
    }

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

    @Override
    public void removeTopping(PartyCookie partyCookie, Topping t) {
        if(partyCookie.getIngredients().contains(t)){
            partyCookie.getIngredientsList().remove(t);
        }
    }
}
