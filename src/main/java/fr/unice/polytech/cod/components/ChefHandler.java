package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.ChefAction;
import fr.unice.polytech.cod.interfaces.OrderStatesAction;
import fr.unice.polytech.cod.interfaces.ScheduleActions;
import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.order.OrderState;
import fr.unice.polytech.cod.pojo.schedule.Schedule;
import fr.unice.polytech.cod.helper.tools.TimeClock;
import fr.unice.polytech.cod.pojo.store.Chef;
import fr.unice.polytech.cod.pojo.store.ChefState;
import fr.unice.polytech.cod.pojo.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;


@Component
public class ChefHandler implements ChefAction {
    @Autowired
    private  ScheduleActions scheduleActions;
    @Autowired
    private  OrderStatesAction orderStatesAction;

    @Override
    public void updateSchedule(Chef chef, Store store) {
        chef.setSchedule(new Schedule(store));
    }

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
    public void getCurrentOrder(Chef chef, TimeClock timeClock, int numberOfDaysBeforeTheOrder) {
        scheduleActions.getOrderToPrepare(chef.getSchedule(), numberOfDaysBeforeTheOrder, timeClock);
    }

    @Override
    public void terminateCurrentOrder(Chef chef) {
        if (chef.getOrderToPrepare().isPresent())
            orderStatesAction.updateState(chef.getOrderToPrepare().get(), OrderState.READY);
    }

    @Override
    public void associateOrder(Chef chef, Order orderToPrepare) {
        if (isAvailable(chef)) {
            chef.setOrderToPrepare(Optional.of(orderToPrepare));
            chef.setState(ChefState.UNAVAILABLE);
            orderToPrepare.setOrderState(OrderState.IN_PROGRESS);
        }
    }

}