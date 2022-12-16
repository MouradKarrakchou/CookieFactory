package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.interfaces.OrderActions;
import fr.unice.polytech.cod.interfaces.OrderStates;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.User;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class OrderComponent implements OrderActions, OrderStates {
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

    @Override
    public void retrieveOrder(List<Order> orderList, Bill bill) throws Exception{
        Order order = bill.getOrder();
        if (orderList.contains(order)){
            order.updateState(OrderState.RETRIEVE);
            orderList.remove(order);
        }else
            throw new Exception("Order doesn't exist");
    }

}
