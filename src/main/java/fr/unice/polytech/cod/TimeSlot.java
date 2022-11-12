package fr.unice.polytech.cod;

import java.sql.Time;
import java.util.Optional;
import java.util.Timer;

public class TimeSlot {
    private TimeClock startTime;
    private TimeClock endTime;
    private Optional<Order> order;
    boolean reserved=false;

    public TimeSlot(TimeClock startTime,TimeClock endTime){
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

    public TimeClock getStartTime() {
        return startTime;
    }

    public TimeClock getEndTime() {
        return endTime;
    }

    public Optional<Order> getOrder() {
        return order;
    }

    /**
     * If there is no order set for this timeSlot return himself else return empty
     * @return
     */
    public Boolean isAvailable() {
        if (this.reserved) return false;
        if (order.isEmpty()) return true;
        else return false;
    }
}
