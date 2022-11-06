package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.OrderState;
import fr.unice.polytech.cod.Schedule;
import fr.unice.polytech.cod.TimeSlot;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Chef {
    Schedule schedule;
    boolean available;
    Optional<Order> orderToPrepare;
    ChefState state;

    public Chef(Schedule schedule) {
        this.schedule = schedule;
        this.available = true;
        this.state = ChefState.AVAILABLE;
    }

    /**
     * Associate the chef with an order, the chef is now unavailable
     * @param orderToPrepare
     */
    public void associateOrder(Order orderToPrepare) {
        this.orderToPrepare = Optional.of(orderToPrepare);
        this.available = false;
    }

    public Optional<Order> getOrderToPrepare() {
        return orderToPrepare;
    }

    public boolean isAvailable() {
        return available;
    }

    public void giveOrder() throws Exception {
        if(this.orderToPrepare.isPresent()){
            Order order = orderToPrepare.get();
            order.setState(OrderState.RETRIEVE);
            this.orderToPrepare = Optional.empty();
        }
        else
            throw new Exception("No associate order");
    }

    public void makeOtherActivityDuringLeftTime() {
        //TODO he can clean, help or take a break
    }

    public void setState(ChefState chefState) {
        this.state = chefState;
    }

    /**
     * gives the Available TimeSlots of this Chef
     * @param date
     * @return
     */
    public List<TimeSlot> getTimeSlotsAvailables(Date date){
        return(this.schedule.getTimeSlots(date));
    }
}
