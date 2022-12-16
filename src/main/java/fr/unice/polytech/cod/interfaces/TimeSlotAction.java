package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;

import java.util.List;

public interface TimeSlotAction {
    List<Interval> timeSlotAvailable(Store store, List<Chef> chefs, int minutes, int numberOfDaysBeforeTheOrder);
}
