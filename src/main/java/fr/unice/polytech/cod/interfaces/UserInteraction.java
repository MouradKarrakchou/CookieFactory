package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;
import java.util.List;
import java.util.Optional;

public interface UserInteraction {

    List<Cookie> viewCatalog(User user);
    List<Store> viewStoreAvailable(User user);
    void recapCart(User user);
    List<Interval> getAvailableIntervals(User user, int minutesNeeded, int numberOfDaysBeforeTheOrder);
    Cart getCart(User user);
    List<Order> getOrders(User user);
    List<Item> getAllItemsFromCart(User user);
    Item getItemFromCart(User user, String itemName) throws Exception;
    StoreLocation getStoreLocation(User user);
    Store getStore(User user);
    Optional<FidelityAccount> getSubscription(User user) ;
    boolean hasFidelityAccount(User user);
    void notify(String message);
    List<Order> getHistory() throws Exception;

}
