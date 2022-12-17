package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.PartyCookie;
import fr.unice.polytech.cod.interfaces.Saleable;
import fr.unice.polytech.cod.pojo.ingredient.Ingredient;
import fr.unice.polytech.cod.pojo.ingredient.Topping;
import fr.unice.polytech.cod.pojo.store.Store;
import org.springframework.stereotype.Component;

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
