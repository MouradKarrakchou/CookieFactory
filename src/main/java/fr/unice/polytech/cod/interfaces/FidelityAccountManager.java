package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FidelityAccountManager {

    void addOrder(Order order);

    void resetDiscount();

    Optional<Discount> getDiscount();
    void subscribeToSurpriseBasket(Store store, int todayDay, int day, int hour, int minute);

    void notify(String message);

    List<Order> getRetrieveOrder();
}
