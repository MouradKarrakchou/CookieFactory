package fr.unice.polytech.cod.food;

import fr.unice.polytech.cod.interfaces.Saleable;
import fr.unice.polytech.cod.interfaces.StockExplorer;
import fr.unice.polytech.cod.pojo.ingredient.*;
import fr.unice.polytech.cod.pojo.store.Store;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class Cookie implements Cloneable {
    protected final String name;
    protected Dough dough;
    protected Flavour flavour;
    protected List<Topping> toppingList; // TODO : Maybe topping should be represented as a set
    protected Mix mix;
    protected Cooking cooking;
    private int preparationTime; // en minutes

    public Cookie(String cookieName, Dough dough, Flavour flavour, List<Topping> toppingList, Mix mix, Cooking cooking, int preparationTime) {
        this.name = cookieName;
        this.dough = dough;
        this.flavour = flavour;
        this.toppingList = toppingList;
        this.mix = mix;
        this.cooking = cooking;
        this.preparationTime = preparationTime;
    }

    public Cookie(Cookie cookie) {
        name = cookie.getName();
        dough = new Dough(cookie.getDough());
        flavour = new Flavour(cookie.getFlavour());
        toppingList = new ArrayList<>();
        cookie.getToppingList().forEach(topping -> toppingList.add(new Topping(topping)));

        mix = cookie.getMix();
        cooking = cookie.getCooking();
        preparationTime = cookie.getPreparationTime();
    }

    public Cooking getCooking() {
        return this.cooking;
    }

    public Mix getMix() {
        return this.mix;
    }


    public String getName() {
        return name;
    }

    public Dough getDough() {
        return dough;
    }

    public Flavour getFlavour() {
        return flavour;
    }

    public List<Topping> getToppingList() {
        return toppingList;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public void setFlavour(Flavour flavour) {
        this.flavour = flavour;
    }

    public void setToppingList(List<Topping> toppingList) {
        this.toppingList = toppingList;
    }

    public void setMix(Mix mix) {
        this.mix = mix;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }


    @Override
    public String toString() {
        return "Cookie{" +
                "cookieName='" + name + '\'' +
                ", dough=" + dough +
                ", flavour=" + flavour +
                ", toppingList=" + toppingList +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == this)
            return true;

        // Check for the instance
        if (!(object instanceof Cookie c))
            return false;

        // Check for the name equality
        return c.name.equals(name);
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setCooking(Cooking cooking) {
        this.cooking = cooking;
    }

    public Cookie clone() {
        Cookie cookie = null;
        try {
            cookie = (Cookie) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        // return the clone
        return cookie;
    }
}
