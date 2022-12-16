package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.ScheduleActions;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Calendar;
import fr.unice.polytech.cod.schedule.DaySlot;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ScheduleComponent implements ScheduleActions {
    @Override
    public List<Interval> getIntervals(Calendar calendar, int minutes, int numberOfDaysBeforeTheOrder) {
        return null;
    }

    @Override
    public DaySlot getDaySlot(Calendar calendar, int numberOfDaysBefore) {
        return null;
    }

    @Override
    public Optional<Order> getOrderToPrepare(Calendar calendar, int numberOfDaysBeforeTheOrder, TimeClock timeClock) {
        return Optional.empty();
    }
}
