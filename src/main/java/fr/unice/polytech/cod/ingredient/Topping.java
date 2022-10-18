package fr.unice.polytech.cod.ingredient;

public class Topping extends Ingredient {
    public Topping(String name, double price, double quantity) {
        super(name, price, quantity);
    }
    @Override
    public Ingredient takePortion(double quanityToExtract) throws Exception {
        if (super.quantity>quanityToExtract) throw new Exception("Tried to take a too big portion");
        this.quantity-=quanityToExtract;
        return new Topping(super.name,super.pricePerg,super.quantity);
    }
}
