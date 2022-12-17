package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.schedule.Interval;
import fr.unice.polytech.cod.pojo.schedule.Schedule;
import fr.unice.polytech.cod.helper.tools.*;

import java.util.List;
import java.util.Optional;

public interface ScheduleActions {

    DaySlot getDaySlot(Schedule schedule, int numberOfDaysBefore);

    Optional<Order> getOrderToPrepare(Schedule schedule, int numberOfDaysBeforeTheOrder, TimeClock timeClock);
}
