package fr.unice.polytech.cod;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

public class StateMachine extends Thread {
    private List<Order> orderList = new ArrayList<>();
    private boolean isActive = true;
    private final int UPDATE_TIME = 1000;


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

    @Override
    public void run() {
        while(isActive){
            removeNotReadyOrders();
            try {sleep(UPDATE_TIME);}
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
    }

    private void checkState(){
        orderList.removeIf(order -> order.getState() != OrderState.READY);
    }

    public void removeNotReadyOrders(){
        orderList.removeIf(order -> order.getState() != OrderState.READY);
    }

}
