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

    public OrderState getOrderState() {
        return orderState;
    }

    public void setState(OrderState state) {
        this.orderState = state;
    }

    public User getUser() {
        return user;
    }

    public Cart getCart() { return cart;}
}
