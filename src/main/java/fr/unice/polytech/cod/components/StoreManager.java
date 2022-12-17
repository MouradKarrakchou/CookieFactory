package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.order.OrderState;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.SurpriseBasket;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class StoreManager implements StoreModifier, StoreAccessor, ApplicationContextAware {

    private static ApplicationContext context;
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

}

