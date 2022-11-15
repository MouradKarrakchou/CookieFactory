package fr.unice.polytech.cod.schedule;

import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    List<DaySlot> daySlots= new ArrayList<>();

    //max number of days before you can order
    static int DAYSINADVANCE=7;

    public Schedule(Store store) {
        for(int k=0;k<DAYSINADVANCE;k++)
            daySlots.add(new DaySlot(store));
    }

    /**
     * Give the available time slots of the Chef
     *
     * @param minutes
     * @param numberOfDaysBeforeTheOrder
     * @return The list of available Interval
     */
    public List<Interval> getIntervals(int minutes,int numberOfDaysBeforeTheOrder) {
        return daySlots.get(numberOfDaysBeforeTheOrder).askForSlotsAvailable(minutes);
    }

    /**
     *
     * @param numberOfDaysBefore
     * @return
     */
    public DaySlot getDaySlot(int numberOfDaysBefore) {
        return daySlots.get(numberOfDaysBefore);
    }
}
