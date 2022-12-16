package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.IntervalManager;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.util.List;

public class IntervalComponent implements IntervalManager {
    @Autowired
    TimeSlotComponent timeSlotComponent;
    @Override
    public void reserve(Interval interval) {
        interval.getTimeSlots().forEach(timeSlot -> timeSlot.setReserved(true));
    }

    @Override
    public void validate(Interval interval, Order order) {
        interval.getTimeSlots().forEach(timeSlot -> timeSlotComponent.associate(timeSlot,order));
    }

    @Override
    public void freedInterval(Interval interval) {
        interval.getTimeSlots().forEach(timeSlot->timeSlotComponent.disassociate(timeSlot));
    }

}