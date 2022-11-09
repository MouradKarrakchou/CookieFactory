package fr.unice.polytech.cod;

import java.sql.Time;

public class Order {
    private OrderState orderState;
    private Cart cart;
    private User user;
    private Time finishTime;

    public Order(Cart cart, User user) {
        this.cart = cart;
        this.orderState = OrderState.PENDING;
        this.user = user;
    }

    public Order(Cart cart, OrderState orderState, User user) {
        this.cart = cart;
        this.orderState = orderState;
        this.user = user;
    }

    public void setFinishTime(Time finishTime){
        this.finishTime = finishTime;
    }

    public Time getFinishTime(){
        return finishTime;
    }

    public OrderState getState() {
        return orderState;
    }

    /**
     * Set the state of the order to the given OrderState
     * If the state is set to READY the finishTime will be set to now.
     * @param state The new state of the order
     */
    public void updateState(OrderState state) {
        if(state == OrderState.READY)
            finishTime = new Time(System.currentTimeMillis());

        this.orderState = state;
    }

    public User getUser() {
        return user;
    }

    public Cart getCart() { return cart;}
}
