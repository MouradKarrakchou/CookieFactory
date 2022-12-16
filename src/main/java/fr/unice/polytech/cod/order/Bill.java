package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.store.Store;

import java.util.Set;

public class Bill {
    Order order;
    int numberOrder;

    public Bill(Order order){
        this.order = order;
        this.numberOrder = Store.orderNumber++;
    }
    public Order getOrder() {
        return order;
    }
}
