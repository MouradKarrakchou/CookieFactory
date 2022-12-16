package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.interfaces.CartActions;
import fr.unice.polytech.cod.interfaces.StoreAccessor;
import fr.unice.polytech.cod.interfaces.StoreFinder;
import fr.unice.polytech.cod.interfaces.UserRequest;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.pojo.StoreLocation;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRequestComponent implements UserRequest {
    @Autowired
    CartActions cartActions;

    @Autowired
    StoreAccessor storeAccessor;

    StoreFinder storeFinder = StoreFinderComponent.getInstance();

    @Override
    public List<Cookie> viewCatalog(Store store) {
        return storeAccessor.getAvailableCookie(store);
    }

    @Override
    public List<Store> viewStoreAvailable() {
        return storeFinder.getStores();
    }

    @Override
    public void recapCart(Cart cart) {
        Display.title("Your cart:");
        cartActions.showCart(cart);
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
