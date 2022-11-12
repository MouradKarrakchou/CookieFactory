package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Chef;

import java.util.List;

public class Schedule {

    DaySlot daySlot;

    public Schedule() {
        daySlot=new DaySlot();
    }
    /**
     * Give the available time slots of the Chef
     * @return
     */
    public List<Interval> getIntervals(int minutes){
        return daySlot.askForSlotsAvailable(minutes);
    }
}
