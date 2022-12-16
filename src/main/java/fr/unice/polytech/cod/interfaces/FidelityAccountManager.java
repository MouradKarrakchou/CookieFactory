package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;

import java.util.List;
import java.util.Optional;

public interface FidelityAccountManager {

    void addOrder(FidelityAccount fidelityAccount, Order order);

    Optional<Discount> getDiscount(FidelityAccount fidelityAccount);

    void subscribeToSurpriseBasket(FidelityAccount fidelityAccount, Store store, int todayDay, int day, int hour, int minute);

    void notify(FidelityAccount fidelityAccount, String message);

    List<Order> getRetrieveOrder(FidelityAccount fidelityAccount);
}
