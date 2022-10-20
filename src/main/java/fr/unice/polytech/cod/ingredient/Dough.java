package fr.unice.polytech.cod.ingredient;

public class Dough extends Ingredient {
    public Dough(String name,double pricePerg, double quantity) {
        super(name, pricePerg, quantity);
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
        return new Dough(super.name,super.pricePerg,super.quantity);
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
