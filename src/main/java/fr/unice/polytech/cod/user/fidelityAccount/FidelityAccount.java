package fr.unice.polytech.cod.user.fidelityAccount;

import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FidelityAccount {
    private final String name;
    private final String email;
    private final String password;
    private int numberOfCommandedCookies;
    private final List<Order> orderList;
    private Discount discount;

    //TODO Ajouter plus tard un UserID
    public FidelityAccount(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberOfCommandedCookies = 0;
        this.orderList = new ArrayList<>();
    }

    public FidelityAccount() {
        this.name = "";
        this.email = "";
        this.password = "";
        this.numberOfCommandedCookies = 0;
        this.orderList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
        Set<Item> items = order.getCart().getItemSet();
        for (Item item : items) numberOfCommandedCookies += item.getQuantity();
        if (numberOfCommandedCookies >= 30) {
            discount = new Discount("Loyalty program", 10);
            numberOfCommandedCookies -= 30;
        }
    }

    public void resetDiscount() {
        this.discount = null;
    }

    public Optional<Discount> getDiscount() {
        if (discount == null)
            return Optional.empty();
        return Optional.of(discount);
    }

    /**
     * Subscribe to a store to receive mail notifications when a surprise basket is available
     *
     * @param store store at which the fidelity account subscribes
     */
    public void subscribeToSurpriseBasket(Store store, int todayDay, int day, int hour, int minute) {
        store.addFidelityAccount(this, todayDay, day, hour, minute);
        //TODO Maybe change the way we chose the date
    }

    public void notify(String message) {
        Display.mailNotifier(message);
    }

    public List<Order> getRetrieveOrder() {
        return orderList.stream().filter(
                order -> order.getOrderState().equals(OrderState.RETRIEVE)).toList();
    }
}
