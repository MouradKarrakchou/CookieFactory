package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.SurpriseBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StoreComponent implements StoreModifier, StoreAccessor {
    private final ChefAction chefAction;
    private final StockExplorer stockExplorer;
    Map<Ingredient, Double> taxes;
    private final OrderStatesAction orderStatesAction;

    @Autowired
    public StoreComponent(ChefAction chefAction, StockExplorer stockExplorer, OrderStatesAction orderStatesAction) {
        this.chefAction = chefAction;
        this.stockExplorer = stockExplorer;
        this.taxes = new HashMap<>();
        this.orderStatesAction = orderStatesAction;
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
    public void updateTaxes(Store store, Ingredient ingredient, double tax) {
        taxes.put(ingredient, tax);
    }

    @Override
    public void setTaxes(Store store, Map<Ingredient, Double> taxes) {
        this.taxes = taxes;
    }

    @Override
    public List<SurpriseBasket> getAllSurpriseBasket(Store store) {
        List<SurpriseBasket> surpriseBaskets = new ArrayList<>();
        getObsoleteOrders(store).forEach(obsoleteOrder -> surpriseBaskets.add(new SurpriseBasket(obsoleteOrder)));
        return surpriseBaskets;
    }

    @Override
    public List<Order> getObsoleteOrders(Store store) {
        return store.getOrderList().stream()
                .filter(order -> order.getOrderState() == OrderState.OBSOLETE)
                .toList();
    }
}
