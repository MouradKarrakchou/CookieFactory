package fr.unice.polytech.cod.pojo.order;

import fr.unice.polytech.cod.pojo.store.Store;

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
