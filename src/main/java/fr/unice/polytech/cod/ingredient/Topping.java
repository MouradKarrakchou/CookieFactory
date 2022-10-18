package fr.unice.polytech.cod.ingredient;

public class Topping extends Ingredient {
    public Topping(String name, double price, double quantity) {
        super(name, price, quantity);
    }
    @Override
    public Ingredient takePortion(double quantityToExtract) throws Exception {
        if (super.quantity> quantityToExtract) throw new Exception("Tried to take a too big portion");
        this.quantity-= quantityToExtract;
        return new Topping(super.name,super.pricePerg,super.quantity);
    }
}
