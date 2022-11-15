package fr.unice.polytech.cod.schedule;

import fr.unice.polytech.cod.order.Order;

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

    /**
     * Set all the timeSlot in the Interval to reserved = true
     */
    public void reserve() {
        timeSlots.forEach(timeSlot -> timeSlot.setReserved(true));
    }

    /**
     * Associate all the timeSlot in the Interval to the given order
     * @param order The order to associate all timeSlot
     */
    public void validate(Order order) {
        timeSlots.forEach(timeSlot -> timeSlot.associate(order));
    }

    /**
     * Reset each timeSlot from the Interval
     */
    public void freedInterval() {
        timeSlots.forEach(TimeSlot::disassociate);
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
