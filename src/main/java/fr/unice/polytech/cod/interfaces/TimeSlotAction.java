package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Chef;

import java.util.List;

public interface TimeSlotAction {
    List<Interval> timeSlotAvailable(List<Chef> chefs, int minutes, int numberOfDaysBeforeTheOrder);
}
