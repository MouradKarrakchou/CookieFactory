package unitTest;

import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.OrderState;
import fr.unice.polytech.cod.StateMachine;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.sql.Time;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateMachineTest {
    private final int STATE_MACHINE_UPDATE_TIME = 500;
    private final int SECURITY = 1000;

    private StateMachine stateMachine;

    @BeforeEach
    void init(){
        // This state machine will be updated every 500ms
        stateMachine = new StateMachine(STATE_MACHINE_UPDATE_TIME);
    }

    @Test
    void addOrder(){
        Order order = new Order(null, null, null);
        order.updateState(OrderState.READY);
        stateMachine.addOrder(order);
        assertEquals(1, stateMachine.dumpOrders().size());
    }

    @Test
    void changeStateToObsolete() throws InterruptedException {
        Order myOrder = new Order(null, OrderState.READY, null);
        long time2hAgo = System.currentTimeMillis() - 7_300_000;
        myOrder.setFinishTime(new Time(time2hAgo));

        stateMachine.addOrder(myOrder);

        // Sleep for a complete stateMachine cycle
        sleep(STATE_MACHINE_UPDATE_TIME + SECURITY);

        assertEquals(OrderState.OBSOLETE, myOrder.getState());
        assertEquals(0, stateMachine.dumpOrders().size());
    }

}
