package fr.unice.polytech.cod;

public class Order {

    OrderState orderState;
    Cart cart;
    User user;

    public Order(Cart cart, OrderState orderState, User user) {
        this.cart = cart;
        this.orderState = orderState;
        this.user = user;
    }

    public String getOrderState() {
        return orderState.toString();
    }

    public void setState(OrderState state) {
        this.orderState = state;
    }

    public User getUser() {
        return user;
    }
}
