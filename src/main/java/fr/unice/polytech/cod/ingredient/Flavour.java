package fr.unice.polytech.cod.ingredient;

public class  Flavour extends Ingredient {
    public Flavour(String name, double price, double quantity) {
        super(name, price, quantity);
    }
    @Override
    public Ingredient takePortion(double quanityToExtract) throws Exception {
        if (super.quantity>quanityToExtract) throw new Exception("Tried to take a too big portion");
        this.quantity-=quanityToExtract;
        return new Flavour(super.name,super.pricePerg,super.quantity);
    }
}
