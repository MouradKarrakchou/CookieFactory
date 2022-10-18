package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.Schedule;

import java.util.List;

public class Chef {
    Schedule schedule;
    Order orderToPrepare;

    public Chef(Schedule schedule) {
        this.schedule = schedule;
    }

    public void associateOrder(Order orderToPrepare) {
        this.orderToPrepare = orderToPrepare;
    }

    public Order getOrderToPrepare() {
        return orderToPrepare;
    }
}
