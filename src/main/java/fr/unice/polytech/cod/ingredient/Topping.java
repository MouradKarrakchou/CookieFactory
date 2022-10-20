package fr.unice.polytech.cod.ingredient;

public class Topping extends Ingredient {
    public Topping(String name, double price, double quantity) {
        super(name, price, quantity);
    }

    /**
     * Permit to create a new Ingredit of the quantity to exctract and reduce this quantity from our object
     * @param quantityToExtract
     * @return
     * @throws Exception
     */
    @Override
    public Ingredient takePortion(double quantityToExtract) throws Exception {
        if (super.quantity> quantityToExtract) throw new Exception("Tried to take a too big portion");
        this.quantity-= quantityToExtract;
        return new Topping(super.name,super.pricePerg,super.quantity);
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
