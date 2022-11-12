package fr.unice.polytech.cod;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Interval implements Comparable {
    private TimeClock startTime;
    private TimeClock endTime;
    private List<TimeSlot> timeSlots;
    public Interval(List<TimeSlot> timeSlots){
        startTime=timeSlots.get(0).getStartTime();
        endTime=timeSlots.get(timeSlots.size()-1).getEndTime();
        this.timeSlots=timeSlots;
    }
    public void reserve(){
        for (TimeSlot timeSlot:timeSlots){
            timeSlot.setReserved(true);
        }
    }
    public void validate(Order order){
        for (TimeSlot timeSlot:timeSlots){
            timeSlot.associate(order);
        }
    }

    public TimeClock getStartTime() {
        return startTime;
    }

    public TimeClock getEndTime() {
        return endTime;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    @Override
    public boolean equals(Object obj) {
        return (compareTo(obj)==0);
    }

    @Override
    public int compareTo(Object o) {
        Interval interval=(Interval) o;
        return(this.startTime.compareTo(interval.startTime));
    }
}
