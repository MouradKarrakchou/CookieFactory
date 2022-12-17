package fr.unice.polytech.cod.food;

import fr.unice.polytech.cod.food.ingredient.*;
import fr.unice.polytech.cod.interfaces.Saleable;
import fr.unice.polytech.cod.interfaces.StockExplorer;
import fr.unice.polytech.cod.store.Store;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class Cookie implements Saleable, Cloneable {
    protected final String name;
    protected Dough dough;
    protected Flavour flavour;
    protected List<Topping> toppingList; // TODO : Maybe topping should be represented as a set
    protected Mix mix;
    protected Cooking cooking;
    private int preparationTime; // en minutes

    @Autowired
    StockExplorer stockExplorer;

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

    private Mix getMix() {
        return this.mix;
    }

    /**
     * Give the set of all ingredients cloned needed to produce one cookie base on its recipe.
     *
     * @return The set of all ingredients cloned needed.
     */
    public Set<Ingredient> getIngredients() {
        Set<Ingredient> ingredients = new HashSet<>();

        if (dough != null) ingredients.add(dough.clone());
        if (flavour != null) ingredients.add(flavour.clone());
        toppingList.forEach(topping -> ingredients.add(topping.clone()));
        return ingredients;
    }

    public ArrayList<Ingredient> getIngredientsList() {
        return new ArrayList<>(getIngredients().stream().toList());
    }

    @Override
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

    @Override
    public double getPrice(Store store) {
        double tax = store.getTax();
        double price = dough.getQuantity() * (dough.getPrice() + tax);
        if (flavour != null)
            price += flavour.getQuantity() * (flavour.getPrice() + tax);
        if (!toppingList.isEmpty()) {
            for (Topping topping : toppingList)
                price += topping.getQuantity() * (topping.getPrice() + tax);
        }
        return price;
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
