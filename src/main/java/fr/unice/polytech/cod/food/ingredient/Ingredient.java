package fr.unice.polytech.cod.food.ingredient;

import java.util.Objects;

public class Ingredient {
    protected String name;
    //price per g
    protected double price;
    protected double quantity;

    public Ingredient(String name, double price, double quantity) {  //Ce constructeur c'est juste pour les tests !?
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
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

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {

        return name;
    }
}
