package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.ChefAction;
import fr.unice.polytech.cod.interfaces.TimeSlotAction;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.DaySlot;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeSlot;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class TimeSlotComponent implements TimeSlotAction {

    @Override
    public List<Interval> timeSlotAvailable(Store store, List<Chef> chefs, int minutes, int numberOfDaysBeforeTheOrder) {
        List<Interval> intervals = new ArrayList<>();
        for (Chef chef : chefs) {
            for (Interval interval : getIntervalsAvailable(chef, minutes, numberOfDaysBeforeTheOrder))
                if (!intervals.contains(interval)) intervals.add(interval);
        }
        Collections.sort(intervals);
        return (intervals);
    }

    /**
     * Gives the Available TimeSlots of this Chef.
     *
     * @param chef - The chef.
     * @param minutes - The slot of time you need.
     * @param numberOfDaysBeforeTheOrder - The number of days before the order.
     * @return intervals available.
     */
    public List<Interval> getIntervalsAvailable(Chef chef, int minutes, int numberOfDaysBeforeTheOrder) {
        return askForSlotsAvailable(chef.getSchedule().getDaySlots().get(numberOfDaysBeforeTheOrder), minutes);
    }

    private List<Interval> askForSlotsAvailable(DaySlot daySlot, int numberOfMinuteNeeded) {
        List<Interval> intervals = new ArrayList<>();
        int numberOfSlotNeeded;
        if (numberOfMinuteNeeded % 15 == 0) numberOfSlotNeeded = numberOfMinuteNeeded / 15;
        else numberOfSlotNeeded = numberOfMinuteNeeded / 15 + 1;
        //Slot available that are one after the other
        int slotAvailableCount = 0;
        for (int k = 0; k < daySlot.getTimeSlots().size(); k++) {
            if (isAvailable(daySlot.getTimeSlots().get(k))) slotAvailableCount++;
            if (slotAvailableCount == numberOfSlotNeeded) {
                //we will see if we can create another Interval with the next Timeslot and without the first TimeSlot of this group.
                slotAvailableCount--;
                intervals.add(createInterval(daySlot, k - numberOfSlotNeeded + 1, numberOfSlotNeeded));
            }
        }
        return intervals;
    }
    private Interval createInterval(DaySlot daySlot, int position, int numberOfSlot) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (int k = 0; k < numberOfSlot; k++) {
            timeSlots.add(daySlot.getTimeSlots().get(position + k));
        }
        return new Interval(timeSlots);
    }

    @Override
    public boolean isAvailable(TimeSlot timeSlot) {
        if (timeSlot.isReserved()) return false;
        if (timeSlot.getOrder().isEmpty()) return true;
        else return false;
    }

    @Override
    public void disassociate(TimeSlot timeSlot) {
        timeSlot.setOrder(Optional.empty());
        timeSlot.setReserved(false);
    }

    @Override
    public void associate(TimeSlot timeSlot, Order order) {
        timeSlot.setOrder(Optional.of(order));
    }

}