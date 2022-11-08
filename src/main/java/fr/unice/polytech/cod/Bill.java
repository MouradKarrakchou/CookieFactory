package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Topping;
import fr.unice.polytech.cod.store.Store;

import java.util.List;

public class Bill {
    Order order;
    int numberOrder;

    public Bill(Order order){
        this.order = order;
        this.numberOrder = Store.orderNumber++;
    }

    public String showBill() {
        String receipt = "";

        String storeName = order.getCart().getStore().getName();
        receipt += "==========" + storeName + "==========\n";
        List<Item> items = order.getCart().getItemList();

        double totalPrice = 0;

        for(Item item : items) {
            Cookie cookie = item.getCookie();
            Dough dough = cookie.getDough();
            Flavour flavour = cookie.getFlavour();
            List<Topping> toppings = cookie.getToppingList();

            receipt += cookie.getName() + ":\n";
            receipt += "    " + dough.toString() + ".........." + dough.getQuantity() * dough.getPricePerGram() + "€\n";
            receipt += "    " + flavour.toString() + ".........." + flavour.getQuantity() * flavour.getPricePerGram() + "€\n";

            totalPrice += dough.getQuantity() * dough.getPricePerGram() + flavour.getQuantity() * flavour.getPricePerGram();

            if(!toppings.isEmpty()) {
                for (Topping topping : toppings) {
                    receipt += "    " + topping.toString() + ".........." + topping.getQuantity() * topping.getPricePerGram() + "€\n";
                    totalPrice += topping.getQuantity() * topping.getPricePerGram();
                }
            }
        }

        receipt += "=========================\n";
        receipt += "Total price.........." + totalPrice + "€\n";

        return receipt;
    }
}
