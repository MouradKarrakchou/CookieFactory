package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.ChefAction;
import fr.unice.polytech.cod.interfaces.StockExplorer;
import fr.unice.polytech.cod.interfaces.StoreAccessor;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreComponent implements StoreModifier, StoreAccessor {
    StockExplorer stockExplorer;
    public TimeClock openHour = new TimeClock(8,0);
    public TimeClock closeHour = new TimeClock(18,0);
    List<Chef> listChef;
    ChefAction chefAction;

    @Autowired
    public StoreComponent(StockExplorer stockExplorer,ChefAction chefAction){
        this.stockExplorer = stockExplorer;
        this.chefAction=chefAction;
    }


    @Override
    public void changeOpeningHour(Store store, TimeClock open, TimeClock close) {
        this.openHour=open;
        this.closeHour=close;
        for (Chef chef: listChef){
            chef.updateSchedule(this);
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
}
