package fr.unice.polytech.cod.food.ingredient;

import java.util.Objects;

public class Ingredient implements Cloneable {
    protected String name;
    protected double price; //price per g
    private double quantity;

    public Ingredient(Ingredient i){
        this.name = i.name;
        this.price = i.price;
        this.quantity = i.quantity;
    }

    public Ingredient(String name, double price, double quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getQuantity(){
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
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

    @Override
    public Ingredient clone() {
        Ingredient ingredient;
        try {
            ingredient = (Ingredient) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        // on renvoie le clone
            return ingredient;
        }

}