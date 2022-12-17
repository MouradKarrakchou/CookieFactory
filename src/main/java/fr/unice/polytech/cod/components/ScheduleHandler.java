package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.ScheduleActions;
import fr.unice.polytech.cod.interfaces.TimeSlotAction;
import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.schedule.Interval;
import fr.unice.polytech.cod.pojo.schedule.Schedule;
import fr.unice.polytech.cod.pojo.schedule.TimeSlot;
import fr.unice.polytech.cod.helper.tools.*;
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
}
