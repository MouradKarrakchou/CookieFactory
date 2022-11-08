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

    public void showBill() {
        String storeName = order.getCart().getStore().getName();
        System.out.println("==========" + storeName + "==========");
        List<Item> items = order.getCart().getItemList();

        double totalPrice = 0;

        for(Item item : items) {
            Cookie cookie = item.getCookie();
            Dough dough = cookie.getDough();
            Flavour flavour = cookie.getFlavour();
            List<Topping> toppings = cookie.getToppingList();

            System.out.println(cookie.getName() + ":");
            System.out.println("    " + dough.toString() + ".........." + dough.getQuantity() * dough.getPricePerGram() + "€");
            System.out.println("    " + flavour.toString() + ".........." + flavour.getQuantity() * flavour.getPricePerGram() + "€");

            totalPrice += dough.getQuantity() * dough.getPricePerGram() + flavour.getQuantity() * flavour.getPricePerGram();

            if(!toppings.isEmpty()) {
                for (Topping topping : toppings) {
                    System.out.println("    " + topping.toString() + ".........." + topping.getQuantity() * topping.getPricePerGram() + "€");
                    totalPrice += topping.getQuantity() * topping.getPricePerGram();
                }
            }
        }

        System.out.println("=========================");
        System.out.println("Total price.........." + totalPrice + "€");
    }
}
