package fr.unice.polytech.cod.pojo.store;

import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.schedule.Schedule;

import java.util.Optional;

public class Chef {
    protected Schedule schedule;
    protected Optional<Order> orderToPrepare;
    protected ChefState state;

    public Chef(Store store) {
        this.schedule = new Schedule(store);
        this.state = ChefState.AVAILABLE;
        orderToPrepare = Optional.empty();
    }

    public void setState(ChefState chefState) {
        this.state = chefState;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Optional<Order> getOrderToPrepare() {
        return orderToPrepare;
    }

    public void setOrderToPrepare(Optional<Order> orderToPrepare) {
        this.orderToPrepare = orderToPrepare;
    }

    public ChefState getState() {
        return state;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
