package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.helper.threadedObjects.MailNotifier;
import fr.unice.polytech.cod.interfaces.IFidelityAccountManager;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.order.OrderState;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.User;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.FidelityAccount;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class FidelityAccountManager implements IFidelityAccountManager, ApplicationContextAware {
    @Autowired
    StoreModifier storeModifier;
    private static ApplicationContext context;

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
        addFidelityAccount(store, fidelityAccount, todayDay, day, hour, minute);
        //TODO Maybe change the way we chose the date
    }

    private void addFidelityAccount(Store store, FidelityAccount fidelityAccount, int todayDay, int day, int hour, int minute) {
        int waitingDay = Math.abs(day - todayDay);
        int waitingTime = waitingDay*24*60*60*1000; //days in milliseconds
        waitingTime += hour*60*60*1000; //hours in milliseconds
        waitingTime += minute*60*1000; //minute in milliseconds
        MailNotifier mailNotifier = new MailNotifier(waitingTime, store, fidelityAccount);
        mailNotifier.setTimeReached();
        store.getFidelityAccountList().add(fidelityAccount);
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
    @Override
    public void addOrder(User user, Order order) {
        user.getUserOrders().add(order);
        FidelityAccount fidelityAccount = user.getFidelityAccount();
        if (fidelityAccount != null)
            addOrder(fidelityAccount, order);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
