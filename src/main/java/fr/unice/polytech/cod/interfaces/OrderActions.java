package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import org.mockito.internal.matchers.Or;

import java.time.Instant;
import java.util.Optional;

public interface OrderActions {
    // Setter
    void cancelOrder(Order order);
    void updateState(Order order, OrderState newState);

    // Getter
    User getUser(Order order);
    boolean isCanceledTwiceInARow(Order order, Instant time);
    double computeTotalPrice(Order order);
    OrderState getOrderState(Order order);
    Optional<Discount> getDiscount();
}
