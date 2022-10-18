package fr.unice.polytech.cod.ingredient;

public class Dough extends Ingredient {
    public Dough(String name,double pricePerg, double quantity) {
        super(name, pricePerg, quantity);
    }

    @Override
    public Ingredient takePortion(double quantityToExtract) throws Exception {
        if (super.quantity> quantityToExtract) throw new Exception("Tried to take a too big portion");
        this.quantity-= quantityToExtract;
        return new Dough(super.name,super.pricePerg,super.quantity);
    }
}
