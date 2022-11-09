package fr.unice.polytech.cod;

import java.util.ArrayList;
import java.util.List;

public class StateMachine extends Thread {
    private final List<Order> orderList = new ArrayList<>();
    private boolean isActive = true;

    private int update_time = 5_000; // The order are checked every 5 seconds
    private final int EXPIRATION_TIME = 7_200_000; // An order expire when 2h is reached

    public StateMachine(){
        start();
    }

    public StateMachine(int update_time){
        this.update_time = update_time;
        start();
    }

    /**
     * Add the order to the StateMachine
     * @param order The order to be pushed to the StateMachine
     */
    public void addOrder(Order order){
        orderList.add(order);
    }

    public void setActive(boolean activity){
        isActive = activity;
    }

    /**
     * For each order in the state machine, if the order has reach the EXPIRATION_TIME
     * The order will be set to OBSOLETE
     */
    private void checkForObsoleteOrder(){
        orderList.stream()
                .filter(order -> System.currentTimeMillis() - order.getFinishTime().getTime() >= EXPIRATION_TIME)
                .forEach(order -> order.updateState(OrderState.OBSOLETE));
    }

    /**
     * For each order in the state machine, if the order has not the state READY then the order should NOT be
     * in the StateMachine and will be removed from it.
     */
    public void removeNotReadyOrders(){
        orderList.removeIf(order -> order.getState() != OrderState.READY);
    }

    public List<Order> dumpOrders(){
        return orderList;
    }

    @Override
    public void run() {
        while(isActive){
            removeNotReadyOrders();
            checkForObsoleteOrder();

            try {sleep(update_time);}
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
    }
}
