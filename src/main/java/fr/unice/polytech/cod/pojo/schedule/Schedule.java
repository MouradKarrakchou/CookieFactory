package fr.unice.polytech.cod.pojo.schedule;

import fr.unice.polytech.cod.schedule.DaySlot;
import fr.unice.polytech.cod.pojo.store.Store;
import java.util.ArrayList;
import java.util.List;

public class Schedule{
    List<DaySlot> daySlots = new ArrayList<>();
    static int DAYSINADVANCE = 7;     //max number of days before you can order

    public Schedule(Store store) {
        for (int k = 0; k < DAYSINADVANCE; k++)
            daySlots.add(new DaySlot(store));
    }

    public List<DaySlot> getDaySlots() {
        return daySlots;
    }

    public void setDaySlots(List<DaySlot> daySlots) {
        this.daySlots = daySlots;
    }

}