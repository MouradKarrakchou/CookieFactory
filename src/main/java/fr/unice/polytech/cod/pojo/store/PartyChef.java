package fr.unice.polytech.cod.pojo.store;

import fr.unice.polytech.cod.food.PartyCookie;

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
