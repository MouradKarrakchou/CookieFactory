package fr.unice.polytech.cod.schedule;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Schedule{
    List<DaySlot> daySlots = new ArrayList<>();

    //max number of days before you can order
    static int DAYSINADVANCE = 7;

    public Schedule(Store store) {
        for (int k = 0; k < DAYSINADVANCE; k++)
            daySlots.add(new DaySlot(store));
    }

    /**
     * Give the available time slots of the Chef
     *
     * @param minutes
     * @param numberOfDaysBeforeTheOrder
     * @return The list of available Interval
     */
    public List<Interval> getIntervals(int minutes, int numberOfDaysBeforeTheOrder) {
        return askForSlotsAvailable(daySlots.get(numberOfDaysBeforeTheOrder), minutes);
    }

    private List<Interval> askForSlotsAvailable(DaySlot daySlot, int numberOfMinuteNeeded) {
        List<Interval> intervals = new ArrayList<>();
        int numberOfSlotNeeded;
        if (numberOfMinuteNeeded % 15 == 0) numberOfSlotNeeded = numberOfMinuteNeeded / 15;
        else numberOfSlotNeeded = numberOfMinuteNeeded / 15 + 1;
        //Slot available that are one after the other
        int slotAvailableCount = 0;
        for (int k = 0; k < daySlot.timeSlots.size(); k++) {
            if (daySlot.timeSlots.get(k).isAvailable()) slotAvailableCount++;
            if (slotAvailableCount == numberOfSlotNeeded) {
                //we will see if we can create another Interval with the next Timeslot and without the first TimeSlot of this group.
                slotAvailableCount--;
                intervals.add(createInterval(daySlot, k - numberOfSlotNeeded + 1, numberOfSlotNeeded));
            }
        }
        return intervals;
    }

    //with position being the position of first slot of the group
    private Interval createInterval(DaySlot daySlot, int position, int numberOfSlot) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int k = 0; k < numberOfSlot; k++) {
            timeSlots.add(daySlot.timeSlots.get(position + k));
        }
        return new Interval(timeSlots);
    }

    public Optional<Order> getOrderToPrepare(int numberOfDaysBeforeTheOrder, TimeClock timeClock) {
        for (TimeSlot timeSlot : daySlots.get(numberOfDaysBeforeTheOrder).timeSlots) {
            if (timeSlot.getStartTime().equals(timeClock)) return timeSlot.getOrder();
        }
        return Optional.empty();
    }

    /**
     * @param numberOfDaysBefore
     * @return
     */
    public DaySlot getDaySlot(int numberOfDaysBefore) {
        return daySlots.get(numberOfDaysBefore);
    }
}
