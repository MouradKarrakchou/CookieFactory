package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeSlot;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;

import java.util.List;

public interface TimeSlotAction {

    /**
     * Gets a list of available TimeSlots from all the employees of the store by Date.
     *
     * @param store store
     * @param chefs chefs
     * @param minutes minutes
     * @param numberOfDaysBeforeTheOrder number of days
     * @return a list of interval
     */
    List<Interval> timeSlotAvailable(Store store, List<Chef> chefs, int minutes, int numberOfDaysBeforeTheOrder);

    /**
     * If there is no order set for this timeSlot return himself else return empty.
     *
     * @param timeSlot - The time slot.
     * @return if the time is available.
     */
    boolean isAvailable(TimeSlot timeSlot);

    void disassociate(TimeSlot timeSlot);

    /**
     * Associate this time slot with the order.
     *
     * @param timeSlot time slot
     * @param order order
     */
    void associate(TimeSlot timeSlot, Order order);

}