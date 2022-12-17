package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.IIntervalManager;
import fr.unice.polytech.cod.interfaces.TimeSlotAction;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.schedule.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntervalManager implements IIntervalManager {
    @Autowired
    private TimeSlotAction timeSlotAction;
    @Override
    public void reserve(Interval interval) {
        interval.getTimeSlots().forEach(timeSlot -> timeSlot.setReserved(true));
    }

    @Override
    public void validate(Interval interval, Order order) {
        interval.getTimeSlots().forEach(timeSlot -> timeSlotAction.associate(timeSlot,order));
    }

    @Override
    public void freedInterval(Interval interval) {
        interval.getTimeSlots().forEach(timeSlot-> timeSlotAction.disassociate(timeSlot));
    }

}