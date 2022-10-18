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

    public Ingredient takePortion(double quantityToExtract) throws Exception {
        return null;
    }

}
