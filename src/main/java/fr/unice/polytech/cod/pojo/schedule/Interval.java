package fr.unice.polytech.cod.pojo.schedule;

import fr.unice.polytech.cod.helper.tools.TimeClock;

import java.util.List;

public class Interval implements Comparable {
    private final TimeClock startTime;
    private final TimeClock endTime;
    private final List<TimeSlot> timeSlots;

    public Interval(List<TimeSlot> timeSlots) {
        startTime = timeSlots.get(0).getStartTime();
        endTime = timeSlots.get(timeSlots.size() - 1).getEndTime();
        this.timeSlots = timeSlots;
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
        if(!(obj instanceof Interval))
            return false;

        return (compareTo(obj) == 0);
    }

    @Override
    public int compareTo(Object o) {
        Interval interval = (Interval) o;
        return (this.startTime.compareTo(interval.startTime));
    }
}
