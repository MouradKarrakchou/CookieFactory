package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class CartHandler implements CartActions, CartPenalty {
    @Autowired
    private  StockExplorer stockExplorer;
    @Autowired
    private  ItemActions itemActions;
    @Autowired
    private  OrderActions orderActions;
    @Autowired
    private IntervalManager intervalManager;
    @Autowired
    private IFidelityAccountManager IFidelityAccountManager;



    @Override
    public boolean addToCart(Cart cart, Item item) {
        Optional<Item> _item =  cart.getItemSet().stream()
                .filter(currentItem -> currentItem.equals(item)).findFirst();

        if (_item.isPresent())
            itemActions.updateQuantity(_item.get(), item.getQuantity());
        else
            cart.getItemSet().add(item);

        if(!stockExplorer.hasEnoughIngredients(cart.getStore().getStock(), itemActions.generateIngredientsNeeded(cart.getItemSet()))){
            removeFromCart(cart, item);
            return false;
        }

        return true;
    }

    @Override
    public Boolean removeFromCart(Cart cart, Item item) {
        Optional<Item> _item =  cart.getItemSet().stream().filter(currentItem -> currentItem.equals(item)).findFirst();
        if (_item.isEmpty())
            return false;

        Item inCartItem = _item.get();
        itemActions.updateQuantity(inCartItem, - item.getQuantity());
        return true;
    }

    @Override
    public Bill validate(Cart cart, User user) throws Exception {
        Set<Ingredient> ingredientsNeeded = itemActions.generateIngredientsNeeded(cart.itemSet);
        if (!stockExplorer.hasEnoughIngredients(cart.getStore().getStock(), ingredientsNeeded))
            throw new Exception("Unavailable Ingredients");

        Order order = new Order(cart, user);

        if (user.getFidelityAccount()!=null)
            useDiscount(user.getFidelityAccount(), order);
        IFidelityAccountManager.addOrder(user, order);
        orderActions.addOrder(cart.getStore().getStock(), cart.getStore().getOrderList(), order, ingredientsNeeded);

        intervalManager.validate(cart.getInterval(), order);

        cart.getItemSet().clear();

        return new Bill(order);
    }

    private void useDiscount(FidelityAccount fidelityAccount, Order order) {
        if(fidelityAccount == null)
            return;
        Optional<Discount> _discount = IFidelityAccountManager.getDiscount(fidelityAccount);
        if(_discount.isEmpty())
            return;

        Discount discount = _discount.get();
        order.setDiscount(discount);
        fidelityAccount.resetDiscount();
    }

    @Override
    public void cancelOrder(Cart cart, Order order) {
        orderActions.removeOrder(cart.getStore().getOrderList(), order);
        intervalManager.freedInterval(cart.getInterval());
        cart.setCanceled(cart.getCanceled() + 1);
        Instant time = Instant.now();

        cancelPenalty(cart, time);
    }

    @Override
    public Set<Ingredient> generateIngredientsNeeded(Cart cart, Set<Item> items) {
        Set<Ingredient> neededIngredients = new HashSet<>();
        // Check the list of items
        for (Item item : items) {
            // Generating all needed ingredients for each item
            for (Ingredient ingredient : itemActions.generateIngredientsNeeded(item)) {
                // Merging all needed ingredients together
                boolean isAdded = false;
                for (Ingredient neededIngredient : neededIngredients) {
                    if (neededIngredient.equals(ingredient)) {
                        neededIngredient.setQuantity(ingredient.getQuantity() + neededIngredient.getQuantity());
                        isAdded = true;
                    }
                }
                if (!isAdded)
                    neededIngredients.add(ingredient);
            }
        }
        return neededIngredients;
    }

    @Override
    public Item findItem(Cart cart, String cookieName) throws Exception {
        Item itemFounded = cart.getItemSet().stream()
                .filter(item -> cookieName.equals(item.getCookie().getName()))
                .findAny()
                .orElse(null);
        if (itemFounded == null)
            throw new Exception("Can't find this item into the cart: " + cookieName);
        else
            return itemFounded;
    }

    @Override
    public int getItemQuantity(Cart cart, String itemName) {
        Item item;
        try {
            item = findItem(cart, itemName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (item == null) return 0;
        return item.getQuantity();
    }

    @Override
    public int getDuration(Cart cart) {
        int duration = 15;
        for(Item item: cart.getItemSet()){
            duration+= item.getCookie().getPreparationTime();
        }
        return duration;
    }

    @Override
    public void add(Cart cart, Item item) {
        cart.getItemSet().add(item);
    }

    @Override
    public boolean isEmpty(Cart cart) {
        return cart.getItemSet().isEmpty();
    }

    @Override
    public void showCart(Cart cart) {
        Display.showItems(cart.getItemSet());
    }

    @Override
    public void cancelPenalty(Cart cart, Instant time) {
        if(cart.getCanceled() == 2) {
            boolean isCanceledTwiceInARow = isCanceledTwiceInARow(cart, time);
            if(isCanceledTwiceInARow)
                penalty(cart, time);
            else
                cart.setCanceled(0);
        }

        cart.setLastTimeCanceled(time);
    }

    @Override
    public boolean isTherePenalty(Cart cart, Instant time) {
        if(cart.getEndPenaltyTime() != null)
            if(time.isAfter(cart.getEndPenaltyTime()))
                cart.setPenalty(false);

        return cart.isPenalty();
    }

    @Override
    public boolean isCanceledTwiceInARow(Cart cart, Instant time) {
        return Duration.between(cart.getLastTimeCanceled(), time).toMinutes() <= 8;
    }

    @Override
    public void penalty(Cart cart, Instant time) {
        cart.setEndPenaltyTime(time.plusSeconds(600));  //10 minutes
        cart.setPenalty(true);
    }

    @Override
    public List<Order> getHistory(FidelityAccount fidelityAccount) throws Exception {
        if(fidelityAccount == null)
            throw new Exception("Your not subscribe to a fidelity account");
        return IFidelityAccountManager.getRetrievedOrder(fidelityAccount);
    }
}
