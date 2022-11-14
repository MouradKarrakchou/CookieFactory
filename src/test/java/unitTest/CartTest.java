package unitTest;

import fr.unice.polytech.cod.*;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.store.Store;
import io.cucumber.java.sl.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest {

    User user;
    Cart cart;
    Order order;
    Store store;
    Interval interval;
    TimeSlot timeSlot;
    List<TimeSlot> timeSlotList;

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
        store.addOrder(order, new HashSet<>());
        cart.setInterval(interval);
        cart.setStore(store);
        cart.cancelOrder(order);

        assertTrue(store.getOrderList().isEmpty());
        assertEquals(1, cart.getCanceled());
    }
}
