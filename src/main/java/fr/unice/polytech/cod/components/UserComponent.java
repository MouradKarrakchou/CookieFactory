package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.pojo.StoreLocation;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.interfaces.UserAction;
import fr.unice.polytech.cod.interfaces.UserInteraction;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserComponent implements UserAction, UserInteraction {
    @Override
    public boolean chooseCookies(User user, Cookie cookie, int quantity) {
        return false;
    }

    @Override
    public Store selectStore(User user, String name) throws InvalidStoreException {
        return null;
    }

    @Override
    public void chooseInterval(User user, Interval interval) {

    }

    @Override
    public Bill validateCart(User user) throws Exception {
        return null;
    }

    @Override
    public void addOrder(User user, Order order) {

    }

    @Override
    public void removeOneItemFromCart(User user, Item item) {

    }

    @Override
    public void subscribeToFidelityAccount(User user, String name, String email, String password) {

    }

    @Override
    public void useDiscount(User user, Order order) {

    }

    @Override
    public boolean cancelOrder(User user, Order order) {
        if (user.getOrders().contains(order) && order.getOrderState().equals(OrderState.PENDING)) {
            user.getCart().cancelOrder(order);
            return true; //Your order has been canceled
        } else
            return false; //Your order is already in progress. You cannot canceled it
    }

    @Override
    public List<Cookie> viewCatalog(User user) {
        return null;
    }

    @Override
    public List<Store> viewStoreAvailable(User user) {
        return null;
    }

    @Override
    public void recapCart(User user) {

    }

    @Override
    public List<Interval> getAvailableIntervals(User user, int minutesNeeded, int numberOfDaysBeforeTheOrder) {
        return null;
    }

    @Override
    public Cart getCart(User user) {
        return null;
    }

    @Override
    public List<Order> getOrders(User user) {
        return null;
    }

    @Override
    public List<Item> getAllItemsFromCart(User user) {
        return null;
    }

    @Override
    public Item getItemFromCart(User user, String itemName) throws Exception {
        return null;
    }

    @Override
    public StoreLocation getStoreLocation(User user) {
        return null;
    }

    @Override
    public Store getStore(User user) {
        return null;
    }

    @Override
    public Optional<FidelityAccount> getSubscription(User user) {
        return Optional.empty();
    }

    @Override
    public boolean hasFidelityAccount(User user) {
        return false;
    }

    @Override
    public void notify(String message) {

    }

    @Override
    public List<Order> getHistory() throws Exception {
        return null;
    }
}
