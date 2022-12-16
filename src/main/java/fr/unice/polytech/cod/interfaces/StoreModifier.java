package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;

public interface StoreModifier {
    /**
     * change the opening hour of the restaurant(to do during the night because it reload all the schedule)
     * @param open
     * @param close
     */
    void changeOpeningHour(Store store, TimeClock open, TimeClock close);
    void addChef(Store store,Chef chef);
}
