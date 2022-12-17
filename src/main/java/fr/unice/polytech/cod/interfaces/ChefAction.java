package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.helper.tools.TimeClock;
import fr.unice.polytech.cod.pojo.store.Chef;
import fr.unice.polytech.cod.pojo.store.Store;

public interface ChefAction {
    void updateSchedule(Chef chef, Store store);

    boolean isAvailable(Chef chef);

    void startWork(Chef chef) throws Exception;

    void getCurrentOrder(Chef chef, TimeClock timeClock, int numberOfDaysBeforeTheOrder);

    void terminateCurrentOrder(Chef chef);

    /**
     * Associate the chef with an order, the chef is now unavailable.
     *
     * @param chef - The chef who will prepare the order.
     * @param orderToPrepare - Order preparing.
     */
    void associateOrder(Chef chef, Order orderToPrepare);
}
