package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.User;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.FidelityAccount;
import java.util.List;
import java.util.Optional;

public interface IFidelityAccountManager {

    void addOrder(FidelityAccount fidelityAccount, Order order);

    Optional<Discount> getDiscount(FidelityAccount fidelityAccount);

    void subscribeToSurpriseBasket(FidelityAccount fidelityAccount, Store store, int todayDay, int day, int hour, int minute);

    void notify(FidelityAccount fidelityAccount, String message);

    List<Order> getRetrievedOrder(FidelityAccount fidelityAccount);

    void addOrder(User user, Order order);

    void addFidelityAccount(Store store, FidelityAccount fidelityAccount, int todayDay, int day, int hour, int minute);


}
