package fr.unice.polytech.cod;

public class Order {

    OrderState orderState;
    Cart cart;
    User user;

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
