package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.Schedule;

import java.util.List;
import java.util.Optional;

public class Chef {
    Schedule schedule;
    Optional<Order> orderToPrepare;

    public Chef(Schedule schedule) {
        this.schedule = schedule;
    }

    public void associateOrder(Order orderToPrepare) {
        this.orderToPrepare = Optional.of(orderToPrepare);
    }

    public Optional<Order> getOrderToPrepare() {
        return orderToPrepare;
    }

    public void giveOrder() {
        if(this.orderToPrepare.isPresent()){
            Order order = orderToPrepare.get();
            order.setOrderState();
        }
    }
}
