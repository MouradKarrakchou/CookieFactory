package fr.unice.polytech.cod.schedule;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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