package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.helper.SmsNotifier;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.helper.UpdatableObject;
import fr.unice.polytech.cod.user.User;

import java.util.Optional;
import java.util.Set;

public class Order extends UpdatableObject {
    protected OrderState orderState;
    protected final Cart cart;
    protected final User user;
    protected Discount discount;
    protected SmsNotifier smsNotifier;
    
    public Order(Cart cart, User user) {
        super(7_200_000); // An order expire when 2h is reached
        this.cart = cart;
        this.orderState = OrderState.PENDING;
        this.user = user;
        smsNotifier = new SmsNotifier(user);
    }

    public Order(Cart cart, OrderState orderState, User user) {
        super(7_200_000); // An order expire when 2h is reached
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


    //TODO on fait comment ?
    /**
     * When the waitingTime is reached the order become OBSOLETE.
     */
    @Override
    public void OnTimeReached() {
        this.orderState = OrderState.OBSOLETE;
        if(cart != null) cart.getStore().addToObsoleteOrders(this);
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