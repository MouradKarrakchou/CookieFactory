package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.store.Store;

import java.util.Set;

public class Bill {
    Order order;
    int numberOrder;
    double totalPrice;

    public Bill(Order order){
        this.order = order;
        this.numberOrder = Store.orderNumber++;
        this.totalPrice = order.getTotalPrice();
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();

        String storeName = order.getCart().getStore().getName();
        receipt.append("===============").append(storeName).append("===============\n");
        Set<Item> items = order.getCart().getItemSet();

        double cookiePrice;
        double totalPrice = 0;

        for(Item item : items) {
            Cookie cookie = item.getCookie();
            //Math.round(price * 100) / 100 allows to round the price to 2 figures
            cookiePrice = Math.round(cookie.getPriceByStore(order.getCart().getStore()) * 100)/100.0;

            receipt.append(cookie.getName()).append("..........").append(cookiePrice).append("€\n");
            totalPrice += cookiePrice;
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

    public Order getOrder() {
        return order;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
