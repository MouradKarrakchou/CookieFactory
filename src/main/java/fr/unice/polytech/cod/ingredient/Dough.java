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
     * verify if 2 objects are of the same types
     * @param ingredient
     * @return
     */
    @Override
    public boolean sameTypes(Ingredient ingredient){
        return false;
    }
}
