package fr.unice.polytech.cod.cucumber;

import fr.unice.polytech.cod.interfaces.OrderStatesAction;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdatableObjectDef {
    private User user;
    private Order order;
    private Order order2;

     @Autowired
     OrderStatesAction orderStatesAction;

    public UpdatableObjectDef() {
    }

    @Given("a mocked user")
    public void aMockedUser() {
        user = Mockito.mock(User.class);
    }

    @Given("an order with the state \"([^\"]*)\"$")
    public void a_order_with_the_state(OrderState orderState) {
        order = new Order(null, orderState, user);
        order.getSmsNotifier().setWaitingTime(500);
        order.setWaitingTime(500);
    }

    @Then("the order should be \"([^\"]*)\"$")
    public void theOrderShouldBe(OrderState orderState) {
        order2 = new Order(null, orderState, user);
        assertEquals(orderState, order2.getOrderState());
    }
}
