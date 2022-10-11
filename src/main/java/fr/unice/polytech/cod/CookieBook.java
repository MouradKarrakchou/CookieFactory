package fr.unice.polytech.cod;

import java.util.ArrayList;
import java.util.List;

public class CookieBook {
    private List<Recipe> recipes;

    public CookieBook(){
        recipes = new ArrayList<>();
        recipes.add(new Recipe("Cookie au chocolat",null,null,null));
        recipes.add(new Recipe("Cookie à la vanille",null,null,null));
        recipes.add(new Recipe("Cookie à la pistache",null,null,null));

        // TODO : Add recipes here.
    }

    public List<Recipe> seeCatalogue() {
        return recipes;
    }
}
