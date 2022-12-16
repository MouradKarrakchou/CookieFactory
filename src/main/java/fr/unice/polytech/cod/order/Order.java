package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.helper.threadedObjects.SmsNotifier;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.helper.threadedObjects.UpdatableObject;
import fr.unice.polytech.cod.user.User;

import java.util.Optional;

public class Order extends UpdatableObject {
    private static final int EXPIRATION_TIME = 7_200_000; // An order expire when 2h is reached
    protected OrderState orderState;
    protected final Cart cart;
    protected final User user;
    protected Discount discount;
    protected SmsNotifier smsNotifier;
    
    public Order(Cart cart, User user) {
        super(EXPIRATION_TIME);
        this.cart = cart;
        this.orderState = OrderState.PENDING;
        this.user = user;
        smsNotifier = new SmsNotifier(user);
    }

    public Order(Cart cart, OrderState orderState, User user) {
        super(EXPIRATION_TIME);
        this.cart = cart;
        this.orderState = orderState;
        this.user = user;
        smsNotifier = new SmsNotifier(user);
    }

    /**
     * Get the current state of th order
     *
     * @return The current OrderState
     */
    public OrderState getOrderState() {
        return orderState;
    }


    /**
     * When the waitingTime is reached the order become OBSOLETE.
     */
    @Override
    public void OnTimeReached() {
        this.orderState = OrderState.OBSOLETE;
    }

    public User getUser() {
        return user;
    }

    public Cart getCart() {
        return cart;
    }

    public Optional<Discount> getDiscount() {
        return Optional.of(discount);
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public SmsNotifier getSmsNotifier() {
        return smsNotifier;
    }


    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void setSmsNotifier(SmsNotifier smsNotifier) {
        this.smsNotifier = smsNotifier;
    }

}