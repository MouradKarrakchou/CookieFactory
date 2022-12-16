package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.ChefAction;
import fr.unice.polytech.cod.interfaces.ScheduleActions;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.Schedule;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ChefComponent implements ChefAction {
    ScheduleActions scheduleActions;

    @Autowired
    public ChefComponent(ScheduleActions scheduleActions){
        this.scheduleActions = scheduleActions;
    }

    @Override
    public void updateSchedule(Chef chef, Store store) {
        // TODO
    }

    @Override
    public void associateOrder(Chef chef, Order orderToPrepare) {
        // TODO
    }

    @Override
    public boolean isAvailable(Chef chef) {
        return false; // TODO
    }

    @Override
    public void startWork(Chef chef) {
        // TODO
    }

    @Override
    public List<Interval> getIntervalsAvailable(Chef chef, int minutes, int numberOfDaysBeforeTheOrder) {
        return null; // TODO
    }

    @Override
    public Schedule getSchedule(Chef chef) {
        return null; // TODO
    }

    @Override
    public void updateCurrentOrder(Chef chef, TimeClock timeClock, int numberOfDaysBeforeTheOrder) {
        // TODO
    }

    @Override
    public Optional<Order> getOrderToPrepare(Chef chef) {
        return Optional.empty(); // TODO
    }

    @Override
    public void terminateCurrentOrder(Chef chef) {
        // TODO
    }
}
