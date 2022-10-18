package fr.unice.polytech.cod.ingredient;

public class Dough extends Ingredient {
    public Dough(String name,double pricePerg, double quantity) {
        super(name, pricePerg, quantity);
    }

    @Override
    public Ingredient takePortion(double quanityToExtract) throws Exception {
        if (super.quantity>quanityToExtract) throw new Exception("Tried to take a too big portion");
        this.quantity-=quanityToExtract;
        return new Dough(super.name,super.pricePerg,super.quantity);
    }
}
