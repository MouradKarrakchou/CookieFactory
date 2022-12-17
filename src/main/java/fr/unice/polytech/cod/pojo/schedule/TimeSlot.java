package fr.unice.polytech.cod.pojo.schedule;

import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.helper.tools.TimeClock;

import java.util.Optional;

public class TimeSlot {
    public static int DURATION = 15000; // in ms
    private TimeClock startTime;
    private TimeClock endTime;
    private Optional<Order> order;
    boolean reserved = false;

    public TimeSlot(TimeClock startTime, TimeClock endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.order = Optional.empty();
    }
    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isReserved() {
        return reserved;
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

    public void setOrder(Optional<Order> order) {
        this.order = order;
    }


}