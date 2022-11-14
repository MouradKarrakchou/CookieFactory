package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.*;
import org.mockito.internal.matchers.Or;

import java.util.List;
import java.util.Optional;

public class Chef extends UpdatableObject{
    private Schedule schedule;
    private Optional<Order> orderToPrepare;
    private ChefState state;
    public static TimeClock STARTMORNINGTIME=new TimeClock(8,0);
    public static TimeClock ENDMORNINGTIME=new TimeClock(12,0);
    public static TimeClock STARTAFTERNOONTIME=new TimeClock(14,0);
    public static TimeClock ENDAFTERNOONTIME=new TimeClock(18,0);

    public Chef() {
        super(TimeSlot.DURATION);
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

    public Optional<Order> getOrderToPrepare() {
        return orderToPrepare;
    }

    public boolean isAvailable() {
        return state == ChefState.AVAILABLE;
    }

    public void startOrder() throws Exception {
        if(this.orderToPrepare.isEmpty())
            throw new Exception("No order to prepare");
        Order order = this.orderToPrepare.get();
        this.state = ChefState.UNAVAILABLE;
        order.updateState(OrderState.IN_PROGRESS);
        super.setWaitingTime(computeIntervalDuration(order.getDuration());
        start();
        order.updateState(OrderState.READY);
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

    public Optional<Order> getWork(TimeClock timeClock){
        return schedule.getDaySlot().getOrderToPrepare(timeClock);
    }
}
