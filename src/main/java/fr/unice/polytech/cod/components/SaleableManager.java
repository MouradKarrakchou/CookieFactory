package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.PartyCookie;
import fr.unice.polytech.cod.interfaces.Saleable;
import fr.unice.polytech.cod.pojo.ingredient.Ingredient;
import fr.unice.polytech.cod.pojo.ingredient.Topping;
import fr.unice.polytech.cod.pojo.store.Store;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
@Component
public class SaleableManager implements Saleable {
    @Override
    public double getPrice(Store store, Cookie cookie) {
        if(cookie instanceof PartyCookie partyCookie){
            return getPriceForPartyCookie(store, partyCookie);
        }
        return getPriceForCookie(store, cookie);
    }

    private double getPriceForCookie(Store store, Cookie cookie){
        double tax = store.getTax();
        double price = cookie.getDough().getQuantity() * (cookie.getDough().getPrice() + tax);
        if (cookie.getFlavour() != null)
            price += cookie.getFlavour().getQuantity() * (cookie.getFlavour().getPrice() + tax);
        if (!cookie.getToppingList().isEmpty()) {
            for (Topping topping : cookie.getToppingList())
                price += topping.getQuantity() * (topping.getPrice() + tax);
        }
        return price;
    }

    /**
     * Give the set of all ingredients cloned needed to produce one cookie base on its recipe.
     *
     * @return The set of all ingredients cloned needed.
     */
    @Override
    public Set<Ingredient> getIngredients(Cookie cookie){
        Set<Ingredient> ingredients = new HashSet<>();
        if (cookie.getDough() != null) ingredients.add(cookie.getDough().clone());
        if (cookie.getFlavour() != null) ingredients.add(cookie.getFlavour().clone());
        cookie.getToppingList().forEach(topping -> ingredients.add(topping.clone()));
        return ingredients;
    }

    @Override
    public ArrayList<Ingredient> getIngredientsList(Cookie cookie) {
        return new ArrayList<>(getIngredients(cookie).stream().toList());
    }


    private double getPriceForPartyCookie(Store store, PartyCookie partyCookie){
        double priceHT = getPriceForCookie(store, partyCookie);
        return getMultiplicator(partyCookie)*priceHT + priceHT*25/100;
    }

    public int getMultiplicator(PartyCookie partyCookie){
        if(partyCookie.getSize() == PartyCookie.CookieSize.L) return 4;
        if(partyCookie.getSize() == PartyCookie.CookieSize.XL) return 5;
        if(partyCookie.getSize() == PartyCookie.CookieSize.XXL) return 6;
        return -1;
    }

}
