package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.pojo.store.Chef;
import fr.unice.polytech.cod.pojo.store.Store;

public interface StoreModifier {

    /**
     * change the opening hour of the restaurant(to do during the night because it reloads all the schedule)
     *
     * @param open open
     * @param close close
     */
    void changeOpeningHour(Store store, TimeClock open, TimeClock close);

    void addChef(Store store, Chef chef);

    void addCookieStore(Store store, Cookie cookie) throws Exception;

    void removeCookieStore(Store store, Cookie cookie) throws Exception;

    void updateSurpriseBasket(Store store);
}
