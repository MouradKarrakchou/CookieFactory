package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.OrderState;
import fr.unice.polytech.cod.Schedule;
import fr.unice.polytech.cod.TimeSlot;

import java.util.List;
import java.util.Optional;

public class Chef {
    Schedule schedule;
    Optional<Order> orderToPrepare;
    ChefState state;

    public Chef(Schedule schedule) {
        this.schedule = schedule;
        this.state = ChefState.AVAILABLE;
    }

    /**
     * Associate the chef with an order, the chef is now unavailable
     * @param orderToPrepare
     */
    public void associateOrder(Order orderToPrepare) {
        this.orderToPrepare = Optional.of(orderToPrepare);
        this.state = ChefState.UNAVAILABLE;
        //Dans
    }

    public Optional<Order> getOrderToPrepare() {
        return orderToPrepare;
    }

    public boolean isAvailable() {
        return state == ChefState.AVAILABLE;
    }

    public void giveOrder() throws Exception {
        if(this.orderToPrepare.isPresent()){
            Order order = orderToPrepare.get();
            order.updateState(OrderState.RETRIEVE);
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
     * @return
     */
    public List<TimeSlot> getTimeSlotsAvailables(){
        return(this.schedule.getTimeSlots());
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
