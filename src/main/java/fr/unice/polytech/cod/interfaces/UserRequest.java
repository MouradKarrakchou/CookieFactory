package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.schedule.Interval;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.Cart;
import fr.unice.polytech.cod.pojo.user.User;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.FidelityAccount;
import java.util.List;
import java.util.Optional;

public interface UserRequest {

    List<Cookie> viewCatalog(Store store);

    List<Store> viewStoreAvailable();

    void recapCart(Cart cart);

    /**
     * Gets a list of available TimeSlots by Date;
     *
     * @return list of intervals
     */
    List<Interval> getAvailableIntervals(Store store, Cart cart, int numberOfDaysBeforeTheOrder);

    Item getItemFromCart(Cart cart, String itemName) throws Exception;
    Optional<FidelityAccount> getSubscription(User user) ;
    boolean hasFidelityAccount(User user);
    void notify(User user, String message);
    List<Order> getHistory(FidelityAccount fidelityAccount) throws Exception;
}
