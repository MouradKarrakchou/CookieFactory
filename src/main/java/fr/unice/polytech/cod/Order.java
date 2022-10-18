package fr.unice.polytech.cod;

public class Order {

    OrderState orderState;
    Cart cart;

    public Order(Cart cart, OrderState orderState) {
        this.cart = cart;
        this.orderState = orderState;
    }

    public String getOrderState() {
        return orderState.toString();
    }
}
