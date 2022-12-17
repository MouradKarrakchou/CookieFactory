package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.helper.threadedObjects.MailNotifier;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.pojo.CookieBook;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.SurpriseBasket;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StoreComponent implements StoreModifier, StoreAccessor {
    @Autowired
    private  ChefAction chefAction;
    @Autowired
    private  StockExplorer stockExplorer;
    @Autowired
    private  CookieBookManager cookieBookManager;
    @Autowired
    private  OrderStatesAction orderStatesAction;

    @Override
    public void changeOpeningHour(Store store, TimeClock open, TimeClock close) {
        store.setOpenHour(open);
        store.setCloseHour(close);
        for (Chef chef: store.getListChef()){
            chefAction.updateSchedule(chef,store);
        }
    }

    @Override
    public List<Cookie> getAvailableCookie(Store store) {
        List<Cookie> cookieAvailable = new ArrayList<>();

        store.getCookieBook().getCookies().stream()
                .filter(cookie -> stockExplorer.hasEnoughIngredients(store.getStock(), cookie.getIngredients()))
                .forEach(cookieAvailable::add);
        return cookieAvailable;
    }

    @Override
    public void addChef(Store store,Chef chef){
        store.getListChef().add(chef);
    }

    @Override
    public void addCookieStore(Store store, Cookie cookie) throws Exception {
        cookieBookManager.addCookieRecipe(store.getCookieBook(), cookie);
    }

    @Override
    public void removeCookieStore(Store store, Cookie cookie) throws Exception {
        cookieBookManager.removeCookieRecipe(store.getCookieBook(), cookie);
    }

    @Override
    public boolean setTax(Store store, String ingredientName, double newPrice) {
        Optional<Ingredient> _stockIngredient = stockExplorer.findIngredient(store.getStock(), ingredientName);
        if(_stockIngredient.isEmpty())
            return false;

        _stockIngredient.get().setPrice(newPrice);
        return true;
    }

    @Override
    public void addFidelityAccount(Store store, FidelityAccount fidelityAccount, int todayDay, int day, int hour, int minute) {
        int waitingDay = Math.abs(day - todayDay);
        int waitingTime = waitingDay*24*60*60*1000; //days in milliseconds
        waitingTime += hour*60*60*1000; //hours in milliseconds
        waitingTime += minute*60*1000; //minute in milliseconds
        MailNotifier mailNotifier = new MailNotifier(waitingTime, store, fidelityAccount);
        mailNotifier.OnTimeReached();
        store.getFidelityAccountList().add(fidelityAccount);
    }

    @Override
    public void updateSurpriseBasket(Store store) {
        getObsoleteOrders(store).forEach(obsoleteOrder -> {
            store.getSurpriseBaskets().add(new SurpriseBasket(obsoleteOrder));
            orderStatesAction.updateState(obsoleteOrder, OrderState.IN_SURPRISE_BASKET);
        });
    }

    @Override
    public List<Order> getObsoleteOrders(Store store) {
        return store.getOrderList().stream()
                .filter(order -> order.getOrderState() == OrderState.OBSOLETE)
                .toList();
    }
}
