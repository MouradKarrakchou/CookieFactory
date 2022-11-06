package fr.unice.polytech.cod;

import java.sql.Time;
import java.util.Optional;
import java.util.Timer;

public class TimeSlot {
    Time startTime;
    Time endTime;
    Optional<Order> order;
    DaySlot daySlot;
    public TimeSlot(Time startTime,Time endTime){
        this.startTime=startTime;
        this.endTime=endTime;
        this.order=Optional.empty();
    }

    /**
     * associate this time slot with the order
     * @param order
     */
    public void associateTimeSlot(Order order, int numberOfMinuteNeeded){
        this.order=Optional.of(order);}

    /**
     * Change the start time of the time slot
     * @param time
     * @return
     */
    public TimeSlot cutTimeSlotByStart(Time time){
        TimeSlot newTimeSlot=new TimeSlot((Time)startTime.clone(),(Time)time.clone());
        startTime=time;
        return newTimeSlot;
    }

    /**
     * Change the end time of the time slot
     * @param time
     * @return
     */
    public TimeSlot cutTimeSlotByEnd(Time time){
        TimeSlot newTimeSlot=new TimeSlot((Time)time.clone(),(Time)endTime.clone());
        endTime=time;
        return newTimeSlot;
    }

    /**
     * If there is no order set for this timeSlot return himself else return empty
     * @return
     */
    public Optional<TimeSlot> timeSlotAvailable() {
        if (order.isEmpty()) return(Optional.of(this));
        else return(Optional.empty());
    }
}
