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
     * Permit to create a new Ingredit of the quantity to exctract and reduce this quantity from our object
     * @param quantityToExtract
     * @return
     * @throws Exception
     */
    public Ingredient takePortion(double quantityToExtract) throws Exception {
        return null;
    }
    /**
     * Multiply the quantity by n. Used in Item to get the total of quantity needed for n cookies.
     *
     * @param n - The number to multiply the quantity by.
     */
    public void multiplyQuantity(int n) {
        quantity *= n;
    }

    /**
     * verify if 2 objects are of the same types
     * @param ingredient
     * @return
     */
    @Override
    public boolean equals(Object ingredient){
        return false;
    }


}
