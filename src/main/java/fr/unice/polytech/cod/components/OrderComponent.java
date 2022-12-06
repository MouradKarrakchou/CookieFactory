package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.OrderActions;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.user.User;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;

import java.time.Instant;
import java.util.Optional;

public class OrderComponent implements OrderActions {
    @Override
    public void cancelOrder(Order order) {
        //TODO
    }

    @Override
    public void updateState(Order order, OrderState newState) {
        //TODO
    }

    @Override
    public User getUser(Order order) {
        return null; //TODO
    }

    @Override
    public boolean isCanceledTwiceInARow(Order order, Instant time) {
        return false; //TODO
    }

    @Override
    public double computeTotalPrice(Order order) {
        return 0; //TODO
    }

    @Override
    public OrderState getOrderState(Order order) {
        return null; //TODO
    }

    @Override
    public Optional<Discount> getDiscount(Order order) {
        return Optional.empty(); //TODO
    }
}
