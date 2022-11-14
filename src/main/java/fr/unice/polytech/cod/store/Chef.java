package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.*;

import java.util.List;
import java.util.Optional;

public class Chef {
    private Schedule schedule;
    private Optional<Order> orderToPrepare;
    private ChefState state;
    public static TimeClock START_MORNING_TIME = new TimeClock(8,0);
    public static TimeClock END_MORNING_TIME = new TimeClock(12,0);
    public static TimeClock START_AFTERNOON_TIME = new TimeClock(14,0);
    public static TimeClock END_AFTERNOON_TIME = new TimeClock(18,0);

    public Chef() {
        this.schedule = new Schedule();
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

    public boolean isAvailable() {
        return state == ChefState.AVAILABLE;
    }

    public void startWork() throws Exception {
        if(this.orderToPrepare.isEmpty())
            throw new Exception("No order to prepare");
        Order order = this.orderToPrepare.get();
        this.state = ChefState.UNAVAILABLE;
        order.updateState(OrderState.IN_PROGRESS);
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
    public List<Interval> getIntervalsAvailable(int minutes){
        return(this.schedule.getIntervals(minutes));
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void updateCurrentOrder(TimeClock timeClock){
        orderToPrepare = schedule.getDaySlot().getOrderToPrepare(timeClock);
    }

    public Optional<Order> getOrderToPrepare() {
        return orderToPrepare;
    }

    public void terminateCurrentOrder(){
        orderToPrepare.ifPresent(order -> order.updateState(OrderState.READY));
    }
}
