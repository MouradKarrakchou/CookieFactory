package fr.unice.polytech.cod.pojo.ingredient;

public class Dough extends Ingredient {
    public Dough(String name,double price, double quantity) {
        super(name, price, quantity);
    }

    public Dough(Dough dough){
        super(dough);
    }

     /**
     * Check if 2 objects are the same (same name, same class)
     *
     * @param object The object to compare
     * @return true/false - The two given ingredients are the same
     */
    @Override
    public boolean equals(Object object){
        // Check for the address
        if (object == this)
            return true;

        // Check for the instance
        if (!(object instanceof Dough d))
            return false;

        // Check for the name equality
        return d.name.equals(name);
    }
}
