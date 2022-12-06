package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.Item;
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
    protected double totalPrice;
    protected SmsNotifier smsNotifier;


    public Order(Cart cart, User user) {
        super(7_200_000); // An order expire when 2h is reached
        this.cart = cart;
        this.orderState = OrderState.PENDING;
        this.user = user;
        this.totalPrice = computeTotalPrice();
        smsNotifier = new SmsNotifier(user);
    }

    private double computeTotalPrice() {
        Set<Item> items = this.cart.getItemSet();
        double totalPrice = 0;
        for (Item item : items) {
            Cookie cookie = item.getCookie();
            double cookiePrice = Math.round(cookie.getPriceByStore(this.cart.getStore()) * 100) / 100.0;
            totalPrice += cookiePrice;
        }
        if (discount != null)
            totalPrice -= totalPrice * discount.getValue();
        return totalPrice;
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

    /**
     * Set the state of the order to the given OrderState
     * If the state is set to READY run the UpdatableObject thread
     *
     * @param state The new state of the order
     */
    public void updateState(OrderState state) {
        this.orderState = state;

        if (orderState == OrderState.READY) {
            startTimer();
            smsNotifier.startTimer();
        }
        if (orderState == OrderState.RETRIEVE) {
            killCurrentThread();
            smsNotifier.killCurrentThread();
        }

    }

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

    public double getTotalPrice() {
        return totalPrice;
    }
}
