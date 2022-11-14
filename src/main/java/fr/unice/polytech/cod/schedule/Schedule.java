package fr.unice.polytech.cod.schedule;

import fr.unice.polytech.cod.store.Store;

import java.util.List;

public class Schedule {

    DaySlot daySlot;

    public Schedule(Store store) {
        daySlot=new DaySlot(store);
    }
    /**
     * Give the available time slots of the Chef
     * @return
     */
    public List<Interval> getIntervals(int minutes){
        return daySlot.askForSlotsAvailable(minutes);
    }

    public DaySlot getDaySlot() {
        return daySlot;
    }
}
