package fr.unice.polytech.cod.user;

import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.interfaces.ItemActions;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.store.Store;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.*;

public class Cart {
    public Store store;
    public final Set<Item> itemSet;
    public Interval interval;
    private int canceled;
    private Instant lastTimeCanceled;
    private Instant endPenaltyTime;
    private boolean penalty;

    @Autowired
    ItemActions itemActions;


    public Cart() {
        this.itemSet = new HashSet<>();
        this.canceled = 0;
        this.penalty = false;
    }

    public void showCart() {
        Display.showItems(itemSet);
    }

    /**
     * Take a list of ingredients and return a set of the ingredients needed
     *
     * @param items
     * @return A set of the ingredients needed
     */
    private Set<Ingredient> generateIngredientsNeeded(Set<Item> items) {
        Set<Ingredient> neededIngredients = new HashSet<>();
        // Check the list of items
        for (Item item : items) {
            // Generating all needed ingredients for each item
            for (Ingredient ingredient : itemActions.generateIngredientsNeeded(item)) {
                // Merging all needed ingredients together
                boolean isAdded = false;
                for (Ingredient neededIngredient : neededIngredients) {
                    if (neededIngredient.equals(ingredient)) {
                        neededIngredient.increaseQuantity(ingredient.getQuantity());
                        isAdded = true;
                    }
                }
                if (!isAdded)
                    neededIngredients.add(ingredient);
            }
        }
        return neededIngredients;
    }

    public Store getStore() {
        return this.store;
    }

    public void add(Item item) {
        this.itemSet.add(item);
    }

    public Interval getInterval() {
        return this.interval;
    }

    public void setInterval(Interval intervals) {
        this.interval = intervals;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getCanceled() {
        return canceled;
    }

    public void setCanceled(int canceled) {
        this.canceled = canceled;
    }

    public Instant getLastTimeCanceled() {
        return lastTimeCanceled;
    }

    public void setLastTimeCanceled(Instant lastTimeCanceled) {
        this.lastTimeCanceled = lastTimeCanceled;
    }

    public Instant getEndPenaltyTime() {
        return endPenaltyTime;
    }

    public void setEndPenaltyTime(Instant endPenaltyTime) {
        this.endPenaltyTime = endPenaltyTime;
    }

    public void setPenalty(boolean penalty) {
        this.penalty = penalty;
    }

    public boolean isPenalty() {
        return penalty;
    }


}
