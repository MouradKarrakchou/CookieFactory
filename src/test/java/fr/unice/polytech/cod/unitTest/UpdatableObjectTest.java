package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.interfaces.OrderStatesAction;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UpdatableObjectTest {
    private Order myPendingOrder;

    @Autowired
    private OrderStatesAction orderStatesAction;

    @BeforeEach
    void Init(){
        myPendingOrder = new Order(null, OrderState.PENDING, null);
    }

    @Test
    void orderStateChangeTest() throws InterruptedException {
        myPendingOrder.setWaitingTime(500);
        assertEquals(OrderState.PENDING, myPendingOrder.getOrderState());

        orderStatesAction.updateState(myPendingOrder, OrderState.READY);
        assertEquals(OrderState.READY, myPendingOrder.getOrderState());

        sleep(1000);
        assertEquals(OrderState.OBSOLETE, myPendingOrder.getOrderState());
    }

    @Test
    void multipleOrderStateChangeTest() throws InterruptedException {
        myPendingOrder.setWaitingTime(10000);
        orderStatesAction.updateState(myPendingOrder, OrderState.READY);
        sleep(1000);
        orderStatesAction.updateState(myPendingOrder, OrderState.READY);
    }
}
