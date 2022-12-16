package fr.unice.polytech.cod.user;

import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.interfaces.ItemActions;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
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


    public Set<Item> getItemSet() {
        return itemSet;
    }


    /**
     * Cancel the client order
     * @param order to cancel
     */
    public void cancelOrder(Order order) {
        store.removeOrder(order);
        this.interval.freedInterval();
        canceled++;
        Instant time = Instant.now();

        cancelPenalty(time);
    }

    /**
     * Manage the necessity of the penalty
     * @param time at which the client has canceled his order
     */
    private void cancelPenalty(Instant time) {
        if(canceled == 2) {
            boolean isCanceledTwiceInARow = isCanceledTwiceInARow(time);
            if(isCanceledTwiceInARow)
                penalty(time);
            else
                canceled = 0;
        }

        lastTimeCanceled = time;
    }

    /**
     * Check if another order has been canceled less than 8 minutes ago
     * @param time at which the order has been canceled
     * @return true if 2 orders has been canceled in 8 minutes or less
     */
    private boolean isCanceledTwiceInARow(Instant time) {
        return Duration.between(lastTimeCanceled, time).toMinutes() <= 8;
    }

    /**
     * Apply the 10 minutes penalty
     * @param time at which the order has been canceled and at which the penalty starts
     */
    private void penalty(Instant time) {
        endPenaltyTime = time.plusSeconds(600); //10 minutes
        penalty = true;
    }

    /**
     * Check if the penalty is over and if so, update the attribute
     * @param time at which we check if the there is the penalty
     * @return true if there still is the penalty
     */
    public boolean isTherePenalty(Instant time) {
        if(endPenaltyTime != null)
            if(time.isAfter(endPenaltyTime))
                penalty = false;

        return penalty;
    }

    public boolean isEmpty() {
        return this.itemSet.isEmpty();
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

    public Item getItem(String itemName) throws Exception {
        Item itemFounded = itemSet.stream()
                .filter(item -> itemName.equals(item.getCookie().getName()))
                .findAny()
                .orElse(null);
        if (itemFounded == null)
            throw new Exception("Can't find this item into the cart: " + itemName);
        else
            return itemFounded;
    }

    public int getItemQuantity(String itemName) {
        Item item = null;
        try {
            item = getItem(itemName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (item == null) return 0;
        return item.getQuantity();
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setInterval(Interval intervals) {
        this.interval = intervals;
    }

    public Interval getInterval() {
         return this.interval;
    }

    public int getDuration() {
        int duration = 15;
        for(Item item: this.itemSet){
            duration+= item.getCookie().getPreparationTime();
        }
        return duration;
    }
    public int getCanceled() {
        return canceled;
    }

    public void add(Item item) {
        this.itemSet.add(item);
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
