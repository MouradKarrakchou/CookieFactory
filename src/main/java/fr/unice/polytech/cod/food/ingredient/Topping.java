package fr.unice.polytech.cod.food.ingredient;

public class Topping extends Ingredient {
    public Topping(String name, double price, double quantity) {
        super(name, price, quantity);
    }


    /**
     * Check if 2 objects are the same (same name, same class)
     *
     * @param object The flavour to compare
     * @return true/false - The two given ingredients are the same
     */
    @Override
    public boolean equals(Object object){
        // Check for the address
        if (object == this)
            return true;

        // Check for the instance
        if (!(object instanceof Topping t))
            return false;

        // Check for the name equality
        return t.name.equals(name);
    }
}
