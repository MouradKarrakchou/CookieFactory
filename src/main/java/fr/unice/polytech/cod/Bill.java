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
        StringBuilder receipt = new StringBuilder();

        String storeName = order.getCart().getStore().getName();
        receipt.append("===============").append(storeName).append("===============\n");
        List<Item> items = order.getCart().getItemList();

        double totalPrice = 0;

        for(Item item : items) {
            Cookie cookie = item.getCookie();
            Dough dough = cookie.getDough();
            Flavour flavour = cookie.getFlavour();
            List<Topping> toppings = cookie.getToppingList();

            receipt.append(cookie.getName()).append(":\n");
            receipt.append("    ").append(dough.toString()).append("..........").append(dough.getQuantity() * dough.getPricePerGram()).append("€\n");
            receipt.append("    ").append(flavour.toString()).append("..........").append(flavour.getQuantity() * flavour.getPricePerGram()).append("€\n");

            totalPrice += dough.getQuantity() * dough.getPricePerGram() + flavour.getQuantity() * flavour.getPricePerGram();

            if(!toppings.isEmpty()) {
                for (Topping topping : toppings) {
                    receipt.append("    ").append(topping.toString()).append("..........").append(topping.getQuantity() * topping.getPricePerGram()).append("€\n");
                    totalPrice += topping.getQuantity() * topping.getPricePerGram();
                }
            }
        }
        if(order.getDiscount().isPresent()){
            Discount discount = order.getDiscount().get();
            totalPrice-=totalPrice*discount.getValue();
            receipt.append("    ").append(discount.getName()).append("..........").append(discount.getValue()).append("%\n");
        }
        receipt.append("===================================\n");
        receipt.append("Total price..........").append(totalPrice).append("€\n");

        System.out.println(receipt);
        return receipt.toString();
    }
}
