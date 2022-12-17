package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Ingredient;

import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.pojo.Stock;
import fr.unice.polytech.cod.user.User;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Component
public class OrderManager implements OrderActions, OrderStatesAction {
    @Autowired
    StockModifier stockModifier;

    @Override
    public void updateState(Order order, OrderState newState) {
        order.setOrderState(newState);

        if (order.getOrderState() == OrderState.READY) {
            order.startTimer();
            order.getSmsNotifier().startTimer();
        }
        if (order.getOrderState() == OrderState.RETRIEVE) {
            order.killCurrentThread();
            order.getSmsNotifier().killCurrentThread();
        }
    }

    @Override
    public User getUser(Order order) {
        return order.getUser();
    }

    @Override
    public double computeTotalPrice(Order order) {
        Set<Item> items = order.getCart().getItemSet();
        double totalPrice = 0;
        for (Item item : items) {
            Cookie cookie = item.getCookie();
            double cookiePrice = Math.round(cookie.getPrice(order.getCart().getStore()) * 100) / 100.0;
            totalPrice += cookiePrice;
        }
        if (order.getDiscount().isPresent())
            totalPrice -= totalPrice * order.getDiscount().get().getValue();
        return totalPrice;
    }

    @Override
    public OrderState getOrderState(Order order) {
        return order.getOrderState();
    }

    @Override
    public Optional<Discount> getDiscount(Order order) {
        return order.getDiscount();
    }

    @Override
    public void addOrder(Stock stock, List<Order> orderList, Order order, Set<Ingredient> ingredientsNeeded) {
        for (Ingredient ingredient : ingredientsNeeded)
            stockModifier.lock(stock, ingredient);
        orderList.add(order);
    }

    @Override
    public void retrieveOrder(List<Order> orderList, Bill bill) throws Exception {
        Order order = bill.getOrder();
        if (orderList.contains(order)) {
            order.setOrderState(OrderState.RETRIEVE);
            orderList.remove(order);
        } else
            throw new Exception("Order doesn't exist");
    }

    @Override
    public void removeOrder(List<Order> orderList, Order order) {
        orderList.remove(order);
    }

}