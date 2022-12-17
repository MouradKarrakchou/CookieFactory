package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.Item;
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
public class UserExplorer implements UserRequest {
    @Autowired
    CartActions cartActions;
    @Autowired
    TimeSlotAction timeSlotAction;
    @Autowired
    StoreAccessor storeAccessor;
    @Autowired
    StoreFinder storeFinder;
    @Autowired
    IFidelityAccountManager IFidelityAccountManager;

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
    public List<Interval> getAvailableIntervals(Store store, Cart cart, int numberOfDaysBeforeTheOrder) {
        return timeSlotAction.timeSlotAvailable(store, store.getListChef(), cartActions.getDuration(cart), numberOfDaysBeforeTheOrder);
    }


    @Override
    public Item getItemFromCart(Cart cart, String itemName) throws Exception {
        return cartActions.findItem(cart, itemName);
    }

    @Override
    public Optional<FidelityAccount> getSubscription(User user) {
        FidelityAccount fidelityAccount = user.getFidelityAccount();
        if(user.getFidelityAccount() == null)
            return Optional.empty();
        return Optional.of(fidelityAccount);
    }

    @Override
    public boolean hasFidelityAccount(User user) {
        return user.getFidelityAccount() != null;
    }

    @Override
    public void notify(User user, String message) {
        FidelityAccount fidelityAccount = user.getFidelityAccount();
        if (fidelityAccount == null)
            Display.smsNok("Anonymous account.");
        else
            Display.smsOk(fidelityAccount.getName(), message);
    }

    @Override
    public List<Order> getHistory(FidelityAccount fidelityAccount) throws Exception {
        if(fidelityAccount == null)
            throw new Exception("Your not subscribe to a fidelity account");
        return IFidelityAccountManager.getRetrievedOrder(fidelityAccount);
        return(cartActions.getHistory(fidelityAccount));
    }
}
