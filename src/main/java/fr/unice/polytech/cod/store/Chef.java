package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.Schedule;
import fr.unice.polytech.cod.schedule.TimeClock;

import java.util.List;
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
    public List<Interval> getIntervalsAvailable(int minutes,int numberOfDaysBeforeTheOrder){
        return(this.schedule.getIntervals(minutes,numberOfDaysBeforeTheOrder));
    }

    public Schedule getSchedule() {
        return schedule;
    }
    public void updateCurrentOrder(TimeClock timeClock,int numberOfDaysBeforeTheOrder){
        orderToPrepare = schedule.getOrderToPrepare(numberOfDaysBeforeTheOrder,timeClock);

    }

    public Optional<Order> getOrderToPrepare() {
        return orderToPrepare;
    }

    public void setOrderToPrepare(Optional<Order> orderToPrepare) {
        this.orderToPrepare = orderToPrepare;
    }
}
