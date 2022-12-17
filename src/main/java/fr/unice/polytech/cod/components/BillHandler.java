package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.BillAction;
import fr.unice.polytech.cod.interfaces.OrderActions;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class BillHandler implements BillAction {
    @Autowired
    OrderActions orderActions;


    @Override
    public double calculateTotalPrice(Bill bill) {
        return orderActions.computeTotalPrice(bill.getOrder());
    }

    @Override
    public String returnBill(Bill bill) {
        StringBuilder receipt = new StringBuilder();

        String storeName = bill.getOrder().getCart().getStore().getName();
        receipt.append("===============").append(storeName).append("===============\n");
        Set<Item> items = bill.getOrder().getCart().getItemSet();

        double cookiesPrice;
        double totalPrice = 0;

        for(Item item : items) {
            Cookie cookie = item.getCookie();
            //Math.round(price * 100) / 100 allows to round the price to 2 figures
            System.out.println(cookie.getPrice(bill.getOrder().getCart().getStore()) * item.getQuantity());
            cookiesPrice = Math.round((cookie.getPrice(bill.getOrder().getCart().getStore())  * item.getQuantity()) * 100)/100.0;

            receipt.append(cookie.getName()).append("..........").append(cookiesPrice).append("€\n");
            totalPrice += cookiesPrice;
        }
        if(bill.getOrder().getDiscount().isPresent()){
            Discount discount = bill.getOrder().getDiscount().get();
            totalPrice-=totalPrice*discount.getValue();
            receipt.append("    ").append(discount.getName()).append("..........").append(discount.getValue()).append("%\n");
        }
        receipt.append("===================================\n");
        receipt.append("Total price..........").append(totalPrice).append("€\n");

        System.out.println(receipt);
        return receipt.toString();
    }
}
