package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Calendar;
import fr.unice.polytech.cod.schedule.DaySlot;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;

import java.util.List;
import java.util.Optional;

public interface Schedule {
    List<Interval> getIntervals(Calendar calendar, int minutes, int numberOfDaysBeforeTheOrder);
    DaySlot getDaySlot(Calendar calendar, int numberOfDaysBefore);
    Optional<Order> getOrderToPrepare(Calendar calendar, int numberOfDaysBeforeTheOrder, TimeClock timeClock);
}
