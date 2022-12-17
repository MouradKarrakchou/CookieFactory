package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.IIntervalManager;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntervalManager implements IIntervalManager {
    @Autowired
    private TimeSlotHandler timeSlotHandler;
    @Override
    public void reserve(Interval interval) {
        interval.getTimeSlots().forEach(timeSlot -> timeSlot.setReserved(true));
    }

    @Override
    public void validate(Interval interval, Order order) {
        interval.getTimeSlots().forEach(timeSlot -> timeSlotHandler.associate(timeSlot,order));
    }

    @Override
    public void freedInterval(Interval interval) {
        interval.getTimeSlots().forEach(timeSlot-> timeSlotHandler.disassociate(timeSlot));
    }

}