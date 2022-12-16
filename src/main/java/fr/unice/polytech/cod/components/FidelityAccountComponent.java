package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.interfaces.FidelityAccountManager;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FidelityAccountComponent implements FidelityAccountManager {

    StoreModifier storeModifier;

    @Autowired
    FidelityAccountComponent(StoreComponent storeModifier) {
        this.storeModifier = storeModifier;
    }

    @Override
    public void addOrder(FidelityAccount fidelityAccount, Order order) {
        fidelityAccount.getOrderList().add(order);
        Set<Item> items = order.getCart().getItemSet();
        for (Item item : items) fidelityAccount.incrementNumberOfCommandedCookies(item.getQuantity());
        if (fidelityAccount.getNumberOfCommandedCookies() >= 30) {
            fidelityAccount.setTheDiscount(new Discount("Loyalty program", 10));
            fidelityAccount.decrementNumberOfCommandedCookies(30);
        }
    }

    @Override
    public Optional<Discount> getDiscount(FidelityAccount fidelityAccount) {
        if (fidelityAccount.getTheDiscount() == null)
            return Optional.empty();
        return Optional.of(fidelityAccount.getTheDiscount());
    }

    /**
     * Subscribe to a store to receive mail notifications when a surprise basket is available
     *
     * @param store store at which the fidelity account subscribes
     */
    @Override
    public void subscribeToSurpriseBasket(FidelityAccount fidelityAccount, Store store, int todayDay, int day, int hour, int minute) {
        storeModifier.addFidelityAccount(store, fidelityAccount, todayDay, day, hour, minute);
        //TODO Maybe change the way we chose the date
    }

    @Override
    public void notify(FidelityAccount fidelityAccount, String message) {
        Display.mailNotifier(message);
    }

    @Override
    public List<Order> getRetrievedOrder(FidelityAccount fidelityAccount) {
        return fidelityAccount.getOrderList().stream().filter(
                order -> order.getOrderState().equals(OrderState.RETRIEVE)).toList();
    }
}
