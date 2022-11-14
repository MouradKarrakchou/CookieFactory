package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.helper.UpdatableObject;
import fr.unice.polytech.cod.user.User;

import java.util.Optional;

public class Order extends UpdatableObject {
    private OrderState orderState;
    private final Cart cart;
    private final User user;
    private Optional<Discount> discount;
    private SmsNotifier smsNotifier;

    public Order(Cart cart, User user) {
        super(7_200_000); // An order expire when 2h is reached
        this.cart = cart;
        this.orderState = OrderState.PENDING;
        this.user = user;
        this.discount = Optional.empty();
        smsNotifier = new SmsNotifier(user);
    }

    public Order(Cart cart, OrderState orderState, User user) {
        super(7_200_000); // An order expire when 2h is reached
        this.cart = cart;
        this.orderState = orderState;
        this.user = user;
        this.discount = Optional.empty();
        smsNotifier = new SmsNotifier(user);
    }

    /**
     * Get the current state of th order
     * @return The current OrderState
     */
    public OrderState getOrderState() {
        return orderState;
    }

    /**
     * Set the state of the order to the given OrderState
     * If the state is set to READY run the UpdatableObject thread
     * @param state The new state of the order
     */
    public void updateState(OrderState state) {
        this.orderState = state;

        if(orderState == OrderState.READY){
            startTimer();
            smsNotifier.startTimer();
        }
        if(orderState == OrderState.RETRIEVE){
            killCurrentThread();
            smsNotifier.killCurrentThread();
        }

    }

    /**
     * When the waitingTime is reached the order become OBSOLETE.
     */
    @Override
    protected void OnTimeReached() {
        this.orderState = OrderState.OBSOLETE;
    }

    public User getUser() {
        return user;
    }

    public Cart getCart() { return cart;}

    public Optional<Discount> getDiscount() {
        return discount;
    }

    public void setDiscount(Optional<Discount> discount) {
        this.discount = discount;
    }

    public int getDuration() {
        return 0;
    }
}
