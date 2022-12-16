package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.IntervalManager;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IntervalComponent implements IntervalManager {

    final List<TimeSlot> timeSlots;

    @Autowired
    public IntervalComponent(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @Override
    public void reserve(Interval interval) {
        timeSlots.forEach(timeSlot -> timeSlot.setReserved(true));
    }

    @Override
    public void validate(Interval interval, Order order) {
        timeSlots.forEach(timeSlot -> timeSlot.associate(order));
    }

    @Override
    public void freedInterval(Interval interval) {
        timeSlots.forEach(TimeSlot::disassociate);
    }
}
