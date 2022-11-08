package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Store;

public class Bill {

    Order order;
    int numberOrder;

    public Bill(Order order){
        this.order = order;
        this.numberOrder = Store.orderNumber++;
    }
}
