package fr.unice.polytech.cod;

import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.user.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UpdatableObjectDef {
    private User user;
    private Order order;

    public UpdatableObjectDef() {
    }

    @Given("a mocked user")
    public void aMockedUser() {
        user = Mockito.mock(User.class);
    }

    @When("the order change to \"([^\"]*)\"$")
    public void theOrderChangeTo(OrderState orderState) {
        order.updateState(orderState);
    }

    @Then("a notification should not be sent")
    public void aNotificationShouldNotBeSent() {
        verify(user, times(0)).notify(anyString());
    }

    @When("the client overdue the timer")
    public void theClientOverdueTheTimer() throws InterruptedException {
        sleep(1000);
    }

    @Then("a notification should have be sent")
    public void aNotificationShouldHaveBeSent() {
        verify(user, times(1)).notify(anyString());
    }

    @Given("an order with the state \"([^\"]*)\"$")
    public void a_order_with_the_state(OrderState orderState) {
        order = new Order(null, orderState, user);
        order.getSmsNotifier().setWaitingTime(500);
        order.setWaitingTime(500);
    }

    @Then("the order should be \"([^\"]*)\"$")
    public void theOrderShouldBe(OrderState orderState) {
        assertEquals(orderState, order.getOrderState());
    }
}
