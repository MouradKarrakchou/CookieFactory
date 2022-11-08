package fr.unice.polytech.cod;

import java.sql.Time;
import java.util.Optional;
import java.util.Timer;

public class TimeSlot {
    Time startTime;
    Time endTime;
    Optional<Order> order;
    boolean reserved=false;

    public TimeSlot(Time startTime,Time endTime){
        this.startTime=startTime;
        this.endTime=endTime;
        this.order=Optional.empty();
    }

    /**
     * associate this time slot with the order
     * @param order
     */
    public void associate(Order order){
        this.order=Optional.of(order);}

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    /**
     * If there is no order set for this timeSlot return himself else return empty
     * @return
     */
    public Optional<TimeSlot> timeSlotAvailable() {
        if (this.reserved) return Optional.empty();
        if (order.isEmpty()) return(Optional.of(this));
        else return(Optional.empty());
    }
}
