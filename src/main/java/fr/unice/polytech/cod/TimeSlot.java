package fr.unice.polytech.cod;

import fr.unice.polytech.cod.order.Order;

import java.util.Optional;

public class TimeSlot {
    public static int DURATION = 15000; // in ms
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
        this.order=Optional.of(order);
    }

    public void disassociate() {
        this.order = Optional.empty();
        this.reserved = false;
    }

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
        else return false;}
}
