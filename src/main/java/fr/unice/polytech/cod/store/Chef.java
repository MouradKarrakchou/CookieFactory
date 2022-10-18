package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.Schedule;

import java.util.List;
import java.util.Optional;

public class Chef {
    Schedule schedule;
    Order orderToPrepare;
    boolean available;
    Optional<Order> orderToPrepare;

    public Chef(Schedule schedule) {
        this.schedule = schedule;
        this.available = true;
    }

    public void associateOrder(Order orderToPrepare) {
        this.orderToPrepare = Optional.of(orderToPrepare);
    }

    public Optional<Order> getOrderToPrepare() {
        return orderToPrepare;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setOrder(Order orderToPrepare) {
        this.orderToPrepare = orderToPrepare;
    }

    public void setAvailability(boolean availability) {
        this.available = availability;
    }

    public void giveOrder() {
        if(this.orderToPrepare.isPresent()){
            Order order = orderToPrepare.get();
            order.setOrderState();
        }
    }
}
