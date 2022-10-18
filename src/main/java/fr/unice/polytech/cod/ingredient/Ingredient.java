package fr.unice.polytech.cod.ingredient;

public class Ingredient {
    protected String name;
    //price per g
    protected double pricePerg;
    protected double quantity;
    public Ingredient(String name,double price,double quantity){
        this.name=name;
        this.pricePerg =price;
        this.quantity=quantity;
    }
    public Ingredient takePortion(double quantityToExtract) throws Exception {
        return null;
    }

}
