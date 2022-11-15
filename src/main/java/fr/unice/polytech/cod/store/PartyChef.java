package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.decorator_pattern.PartyCookieComponent;

import java.util.ArrayList;

public class PartyChef extends Chef{
    ArrayList<PartyCookieComponent> cookieRecipes;
    public PartyChef(Store store) {
        super(store);
        this.cookieRecipes = new ArrayList<>();
    }

    public ArrayList<PartyCookieComponent> getRecipes() {
        return this.cookieRecipes;
    }
}
