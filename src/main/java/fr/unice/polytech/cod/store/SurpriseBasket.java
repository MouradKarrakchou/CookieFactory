package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;

public class SurpriseBasket {
    Order order;

    public SurpriseBasket(Order order) {
        this.order = order;
    }

    /**
     * As the user wants to see the description and the price of the surprise basket, a bill is created
     * @return a bill containing description and price
     */
    public Bill getDescription() {
        return new Bill(order);
    }
}
