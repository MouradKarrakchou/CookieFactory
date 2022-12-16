package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.SurpriseBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreComponent implements StoreModifier, StoreAccessor, StoreAction {


    private final ChefAction chefAction;
    private final StockExplorer stockExplorer;

    @Autowired
    public StoreComponent(ChefAction chefAction, StockExplorer stockExplorer) {
        this.chefAction = chefAction;
        this.stockExplorer = stockExplorer;
    }


    @Override
    public void changeOpeningHour(Store store, TimeClock open, TimeClock close) {
        store.setOpenHour(open);
        store.setCloseHour(close);
        for (Chef chef: store.getListChef()){
            chefAction.updateSchedule(chef,store);
        }
    }


    /**
     * Return the cookies a store can make based on the stock of the store and the recipes in the store's cookie book
     * @return The list of available cookies
     */
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
    /**
     * Check if there are new obsoletes orders for Too Good to Go
     */
    public void checkObsoleteOrders(Store store) {
        if(!store.getObsoleteOrders().isEmpty())
            for(Order order : store.getObsoleteOrders())
                store.getSurpriseBaskets().add(new SurpriseBasket(order));
    }

    public void addToObsoleteOrders(Store store,Order order) {
        store.getObsoleteOrders().add(order);
    }

}
