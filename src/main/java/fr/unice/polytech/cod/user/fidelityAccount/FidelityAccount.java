package fr.unice.polytech.cod.user.fidelityAccount;

import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.MailNotifier;
import fr.unice.polytech.cod.store.Store;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FidelityAccount {
    private String name;
    private String email;
    private String password;
    private int numberOfCommandedCookies;
    private List<Order> orderList;
    private Optional<Discount> discount;

    //TODO Ajouter plus tard un UserID
    public FidelityAccount(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.numberOfCommandedCookies = 0;
        this.orderList =  new ArrayList<>();
        this.discount = Optional.empty();
    }
    public FidelityAccount(){
        this.name = "";
        this.email = "";
        this.password = "";
        this.numberOfCommandedCookies = 0;
        this.orderList = new ArrayList<>();
        this.discount = Optional.empty();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getNumberOfCommandedCookies() {
        return numberOfCommandedCookies;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
        List<Item> items = order.getCart().getItemList();
        for(Item item : items) numberOfCommandedCookies+= item.getQuantity();
        if(numberOfCommandedCookies >= 30){
            this.discount = Optional.of(new Discount("Loyalty program",10));
            numberOfCommandedCookies-=30;
        }
    }
    public Optional<Discount> resetDiscount() {
        return this.discount = Optional.empty();
    }
    public Optional<Discount> getDiscount() {
        return this.discount;
    }

    /**
     * Subscribe to a store to receive mail notifications when a surprise basket is available
     * @param store store at which the fidelity account subscribes
     */
    public void subscribeToSurpriseBasket(Store store, int todayDay, int day, int hour, int minute) {
        store.addFidelityAccount(this, todayDay, day, hour, minute);
        //TODO Maybe change the way we chose the date
    }

    public void notify(String message) {
        Display.mailNotifier(message);
    }

    public List<Order> getRetrieveOrder(){
        return orderList.stream().filter(
                order -> order.getOrderState().equals(OrderState.RETRIEVE)).toList();
    }
}
