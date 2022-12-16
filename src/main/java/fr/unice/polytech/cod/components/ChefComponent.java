package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.ChefAction;
import fr.unice.polytech.cod.interfaces.OrderStatesAction;
import fr.unice.polytech.cod.interfaces.ScheduleActions;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.Schedule;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.ChefState;
import fr.unice.polytech.cod.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class ChefComponent implements ChefAction {
    private final ScheduleActions scheduleActions;
    private final OrderStatesAction orderStatesAction;

    @Autowired
    public ChefComponent(ScheduleActions scheduleActions, OrderStatesAction orderStatesAction) {
        this.scheduleActions = scheduleActions;
        this.orderStatesAction = orderStatesAction;
    }

    @Override
    public void updateSchedule(Chef chef, Store store) {
        chef.setSchedule(new Schedule(store));
    }

    /*
    @Override
    public void associateOrder(Chef chef, Order orderToPrepare) {
        chef.setOrderToPrepare(Optional.of(orderToPrepare));
    }*/

    @Override
    public boolean isAvailable(Chef chef) {
        return chef.getState() == ChefState.AVAILABLE;
    }

    @Override
    public void startWork(Chef chef) throws Exception {
        if (chef.getOrderToPrepare().isEmpty())
            throw new Exception("No order to prepare");
        Order order = chef.getOrderToPrepare().get();
        chef.setState(ChefState.UNAVAILABLE);
        order.setOrderState(OrderState.IN_PROGRESS);
    }

    @Override
    public List<Interval> getIntervalsAvailable(Chef chef, int minutes, int numberOfDaysBeforeTheOrder) {
        return scheduleActions.getIntervals(chef.getSchedule(), minutes, numberOfDaysBeforeTheOrder);
    }

    @Override
    public void updateCurrentOrder(Chef chef, TimeClock timeClock, int numberOfDaysBeforeTheOrder) {
        scheduleActions.getOrderToPrepare(chef.getSchedule(), numberOfDaysBeforeTheOrder, timeClock);
    }

    @Override
    public void terminateCurrentOrder(Chef chef) {
        if (chef.getOrderToPrepare().isPresent())
            orderStatesAction.updateState(chef.getOrderToPrepare().get(), OrderState.READY);
    }

}