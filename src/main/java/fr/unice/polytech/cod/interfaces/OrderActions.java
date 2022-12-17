package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.Stock;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.user.User;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface OrderActions {


    User getUser(Order order);

    double computeTotalPrice(Order order);

    Optional<Discount> getDiscount(Order order);

    void addOrder(Stock stock, List<Order> orderList, Order order, Set<Ingredient> ingredientsNeeded);


    void removeOrder(List<Order> orderList, Order order);
}
