package fr.unice.polytech.cod.pojo.store;

import fr.unice.polytech.cod.pojo.order.Bill;
import fr.unice.polytech.cod.order.Order;

public class SurpriseBasket {

    Order order;

    public SurpriseBasket(Order order) {
        this.order = order;
    }

    /**
     * Bill for the user trough Too Good To Go
     * @return a bill containing description and price
     */
    public Bill getDescription() {
        return new Bill(order);
    }
}
