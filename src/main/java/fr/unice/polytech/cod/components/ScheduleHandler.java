package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.ScheduleActions;
import fr.unice.polytech.cod.interfaces.TimeSlotAction;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ScheduleHandler implements ScheduleActions {
    @Autowired
    private TimeSlotAction timeSlotAction;
    @Override
    public List<Interval> getIntervals(Schedule schedule, int minutes, int numberOfDaysBeforeTheOrder) {
        return askForSlotsAvailable(schedule.getDaySlots().get(numberOfDaysBeforeTheOrder), minutes);
    }

    @Override
    public DaySlot getDaySlot(Schedule schedule, int numberOfDaysBefore) {
        return schedule.getDaySlots().get(numberOfDaysBefore);
    }

    @Override
    public Optional<Order> getOrderToPrepare(Schedule schedule, int numberOfDaysBeforeTheOrder, TimeClock timeClock) {
        for (TimeSlot timeSlot : schedule.getDaySlots().get(numberOfDaysBeforeTheOrder).getTimeSlots()) {
            if (timeSlot.getStartTime().equals(timeClock)) return timeSlot.getOrder();
        }
        return Optional.empty();
    }

    private List<Interval> askForSlotsAvailable(DaySlot daySlot, int numberOfMinuteNeeded) {
        List<Interval> intervals = new ArrayList<>();
        int numberOfSlotNeeded;
        if (numberOfMinuteNeeded % 15 == 0) numberOfSlotNeeded = numberOfMinuteNeeded / 15;
        else numberOfSlotNeeded = numberOfMinuteNeeded / 15 + 1;
        //Slot available that are one after the other
        int slotAvailableCount = 0;
        for (int k = 0; k < daySlot.getTimeSlots().size(); k++) {
            if (timeSlotAction.isAvailable(daySlot.getTimeSlots().get(k))) slotAvailableCount++;
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
            timeSlots.add(daySlot.getTimeSlots().get(position + k));
        }
        return new Interval(timeSlots);
    }
}
