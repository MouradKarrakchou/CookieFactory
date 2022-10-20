package fr.unice.polytech.cod.ingredient;

public class Ingredient {
    protected String name;
    //price per g
    protected double pricePerg;
    protected double quantity;

    public Ingredient(String name, double price, double quantity) {
        this.name = name;
        this.pricePerg = price;
        this.quantity = quantity;
    }

    /**
     * Generate a new Ingredient of the quantity given quantity.
     * Reduce the quantity from the called Ingredient.
     *
     * @param quantityToExtract - The quantity to take from the called ingredient
     * @return The new ingredient generated from the called Ingredient
     * @throws NotEnoughQuantityException If the quantity asked is above the quantity of the called ingredient.
     */
    public Ingredient split(double quantityToExtract) throws NotEnoughQuantityException {
        if (quantityToExtract > quantity)
            throw new NotEnoughQuantityException();

        quantity -= quantityToExtract;
        return new Ingredient(name, pricePerg, quantityToExtract);
    }

    /**
     * Multiply the quantity by n. Used in Item to get the total of quantity needed for n cookies.
     *
     * @param n - The number to multiply the quantity by.
     */
    public void multiplyQuantity(int n) {
        quantity *= n;
    }

    public void increaseQuantity(double quantity){
        this.quantity += quantity;
    }

    public double getQuantity(){
        return quantity;
    }


    /**
     * Check if 2 objects are the same (same name, same class)
     *
     * @param object The ingredient to compare
     * @return true/false - The two given ingredients are the same
     */
    @Override
    public boolean equals(Object object){
        // Check for the address
        if (object == this)
            return true;

        // Check for the instance
        if (!(object instanceof Ingredient i))
            return false;

        // Check for the name equality
        return i.name.equals(name);
    }
}
