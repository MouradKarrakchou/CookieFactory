package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.store.Store;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Cart {
    private Store store;
    private List<Item> itemList;
    private Interval interval;
    private int canceled;
    private Instant lastTimeCanceled;
    private Instant endPenaltyTime;
    private boolean penalty;


    public Cart() {
        this.itemList = new ArrayList<>();
        this.canceled = 0;
        this.penalty = false;
    }

    public void showCart() {
        Display.showItems(itemList);
    }

    /**
     * If the store as the ingredients, add an item to the cart
     *
     * @param item
     * @return
     */
    public boolean addToCart(Item item) {
        if (!store.hasEnoughIngredients(item.generateIngredientsNeeded()))
            return false;

        itemList.add(item);
        return true;
    }

    /**
     * Remove one from the quantity of the item, if the item is present as one copy, then it is deleted
     *
     * @param item The item present in the cart.
     */
    public void removeOneFromCart(Item item) {
        if (item.getQuantity() == 1)
            itemList.remove(item);
        else
            item.updateQuantity(-1);
    }

    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Validdte the cart and create an order, that is added to the user and the sore.
     * The cart is validated only if the store has all the ingredients needed
     *
     * @param user
     * @return
     */
    public Bill validate(User user) throws Exception {
        Set<Ingredient> ingredientsNeeded = generateIngredientsNeeded(this.itemList);
        if (!store.hasEnoughIngredients(ingredientsNeeded))
            throw new Exception("Ingrédients indisponibles");

        Order order = new Order(this, user);
        if (user.hasDiscount())
            user.useDiscount(order);
        user.addOrder(order);
        store.addOrder(order, ingredientsNeeded);

        this.interval.validate(order);
        itemList.clear();

        return new Bill(order);
    }

    public void cancelOrder(Order order) {
        store.removeOrder(order);
        this.interval.freedInterval();
        canceled++;
        Instant time = Instant.now();

        cancelPenalty(time);
    }

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

    private boolean isCanceledTwiceInARow(Instant time) {
        return Duration.between(lastTimeCanceled, time).toMinutes() <= 8;
    }

    private void penalty(Instant time) {
        endPenaltyTime = time.plusSeconds(600); //10 minutes
        penalty = true;
    }

    public boolean isTherePenalty(Instant time) {
        if(time.isAfter(endPenaltyTime))
            penalty = false;

        return penalty;
    }

    public boolean isEmpty() {
        return this.itemList.isEmpty();
    }

    /**
     * Take a list of ingredients and return a set of the ingredients needed
     *
     * @param items
     * @return A set of the ingredients needed
     */
    private Set<Ingredient> generateIngredientsNeeded(List<Item> items) {
        Set<Ingredient> neededIngredients = new HashSet<>();
        // Check the list of items
        for (Item item : items) {
            // Generating all needed ingredients for each item
            for (Ingredient ingredient : item.generateIngredientsNeeded()) {
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
        Item itemFounded = itemList.stream()
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

}
