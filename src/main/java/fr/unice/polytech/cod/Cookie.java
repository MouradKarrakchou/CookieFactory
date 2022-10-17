package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Topping;

import java.util.List;

public class Cookie {
    private String cookieName;
    private Dough dough;
    private Flavour flavour;
    private List<Topping> toppingList;

    public Cookie(String cookieName, Dough dough, Flavour flavour, List<Topping> toppingList){
        this.cookieName = cookieName;
        this.dough = dough;
        this.flavour = flavour;
        this.toppingList = toppingList; //TODO: Make sure the given topping list isn't > 3
    }

    public Cookie(String testCookie, Flavour flavour) {
    }

    public String getCookieName() {
        return cookieName;
    }

    @Override
    public String toString() {
        return "Cookie{" +
                "cookieName='" + cookieName + '\'' +
                ", dough=" + dough +
                ", flavour=" + flavour +
                ", toppingList=" + toppingList +
                '}';
    }
}
