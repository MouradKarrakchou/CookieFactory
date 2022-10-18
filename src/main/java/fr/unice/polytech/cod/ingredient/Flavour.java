package fr.unice.polytech.cod.ingredient;

public class  Flavour extends Ingredient {
    public Flavour(String name, double price, double quantity) {
        super(name, price, quantity);
    }
    @Override
    public Ingredient takePortion(double quantityToExtract) throws Exception {
        if (super.quantity> quantityToExtract) throw new Exception("Tried to take a too big portion");
        this.quantity-= quantityToExtract;
        return new Flavour(super.name,super.pricePerg,super.quantity);
    }
}
