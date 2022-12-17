package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.components.CartHandler;
import fr.unice.polytech.cod.interfaces.ItemActions;
import fr.unice.polytech.cod.interfaces.OrderActions;
import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.pojo.schedule.TimeSlot;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.Cart;
import fr.unice.polytech.cod.pojo.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CartTest {
    User user;
    Cart cart;
    Order order;
    Store store;
    Interval interval;
    TimeSlot timeSlot;
    List<TimeSlot> timeSlotList;

    @Autowired
    OrderActions orderActions;

    @Autowired
    ItemActions itemActions;

    @Autowired
    CartHandler cartHandler;

    @BeforeEach
    void initialisation() {
        cart = new Cart();
        order = new Order(cart, user);
        store = new Store("Store");
        timeSlotList = new ArrayList<>();
        timeSlot = new TimeSlot(new TimeClock(8, 0), new TimeClock(8,15));
        timeSlotList.add(timeSlot);
        interval = new Interval(timeSlotList);
    }

    @Test
    void cancelOrderTest() {
        orderActions.addOrder(store.getStock(), store.getOrderList(), order, itemActions.generateIngredientsNeeded(order.getCart().getItemSet()));
        cart.setInterval(interval);
        cart.setStore(store);
        cartHandler.cancelOrder(order.getCart(), order);

        assertTrue(store.getOrderList().isEmpty());
        assertEquals(1, cart.getCanceled());
    }
}
