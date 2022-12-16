package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.ChefAction;
import fr.unice.polytech.cod.interfaces.TimeSlotAction;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeSlot;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class TimeSlotComponent implements TimeSlotAction {
    ChefAction chefAction;

    @Override
    public List<Interval> timeSlotAvailable(TimeSlot timeSlot, Store store, List<Chef> chefs, int minutes, int numberOfDaysBeforeTheOrder) {
        List<Interval> intervals = new ArrayList<>();
        for (Chef chef : chefs) {
            for (Interval interval : chefAction.getIntervalsAvailable(chef, minutes, numberOfDaysBeforeTheOrder))
                if (!intervals.contains(interval)) intervals.add(interval);
        }
        Collections.sort(intervals);
        return (intervals);
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