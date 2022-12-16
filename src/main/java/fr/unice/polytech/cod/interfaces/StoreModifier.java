package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;

public interface StoreModifier {
    /**
     * change the opening hour of the restaurant(to do during the night because it reloads all the schedule)
     * @param open
     * @param close
     */
    void changeOpeningHour(Store store, TimeClock open, TimeClock close);
    void addChef(Store store,Chef chef);

    /**
     * Update the current price of an ingredient.
     * @param store The store where the ingredient is present
     * @param ingredient The ingredient to update the price of
     * @param newPrice The new price according to a tax
     * @return boolean : The ingredient's price has been updated
     */
    boolean setTax(Store store, String ingredient, double newPrice);
}
