package fr.unice.polytech.cod;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CancelOrder {

    User user;
    Order pendingOrder;
    Order inProgressOrder;

    @When("the user try to cancel his order at the state {string}")
    public void the_user_try_to_cancel_his_order_at_the_state(String string) {
        user.cancelOrder(pendingOrder);
        user.cancelOrder(inProgressOrder);
    }

    @Then("the order is canceled")
    public void the_order_is_canceled() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user is notified")
    public void the_user_is_notified() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the order cannot be canceled")
    public void the_order_cannot_be_canceled() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
