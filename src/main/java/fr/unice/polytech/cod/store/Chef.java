package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.Schedule;
import java.util.Optional;

public class Chef {
    Schedule schedule;
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

    public void setOrder(Order order) {
        this.orderToPrepare = Optional.of(order);
        schedule.startTimer();
    }

    public void setAvailability(boolean availability) {
        this.available = availability;
    }

    public void giveOrder() throws Exception {
        if(this.orderToPrepare.isPresent()){
            Order order = orderToPrepare.get();
            this.orderToPrepare = Optional.empty();
        }
        else
            throw new Exception("No associate order");
    }

    public void makeOtherActivityDuringLeftTime() {
        //TODO he can clean, help or take a break
    }
}
