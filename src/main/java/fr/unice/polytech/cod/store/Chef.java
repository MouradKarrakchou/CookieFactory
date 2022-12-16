package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Schedule;

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

    public void updateSchedule(Store store){
        this.schedule = new Schedule(store);
    }

    public void makeOtherActivityDuringLeftTime() {
        //TODO he can clean, help or take a break
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
