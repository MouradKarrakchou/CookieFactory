package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.Schedule;

public class Chef {
    Schedule schedule;
    Order orderToPrepare;
    boolean available;

    public Chef(Schedule schedule) {
        this.schedule = schedule;
        this.available = true;
    }

    public Order getOrderToPrepare() {
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
}
