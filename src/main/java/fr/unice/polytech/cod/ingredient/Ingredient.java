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
     * verify if 2 objects are of the same types
     * @param ingredient
     * @return
     */
    public boolean sameTypes(Ingredient ingredient){
        return false;
    }


}
