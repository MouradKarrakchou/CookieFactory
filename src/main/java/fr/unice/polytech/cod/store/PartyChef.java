package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.decorator_pattern.PartyCookie;

import java.util.ArrayList;

public class PartyChef extends Chef{
    ArrayList<PartyCookie> cookieRecipes;
    public PartyChef(Store store) {
        super(store);
        this.cookieRecipes = new ArrayList<>();
    }

    public ArrayList<PartyCookie> getRecipes() {
        return this.cookieRecipes;
    }
}
