package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.Schedule;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface ChefAction {
    void updateSchedule(Chef chef, Store store);
    void associateOrder(Chef chef,Order orderToPrepare);
    boolean isAvailable(Chef chef);
    void startWork(Chef chef) throws Exception;
    List<Interval> getIntervalsAvailable(Chef chef,int minutes,int numberOfDaysBeforeTheOrder);
    void updateCurrentOrder(Chef chef,TimeClock timeClock, int numberOfDaysBeforeTheOrder);
    void terminateCurrentOrder(Chef chef);
}
