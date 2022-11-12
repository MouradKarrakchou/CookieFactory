package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Store;

import java.util.List;

public class Bill {
    Order order;
    int numberOrder;

    public Bill(Order order){
        this.order = order;
        this.numberOrder = Store.orderNumber++;
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();

        String storeName = order.getCart().getStore().getName();
        receipt.append("===============").append(storeName).append("===============\n");
        List<Item> items = order.getCart().getItemList();

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
}
