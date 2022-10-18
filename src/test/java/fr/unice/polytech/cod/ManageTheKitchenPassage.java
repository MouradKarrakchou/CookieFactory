package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Chef;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class ManageTheKitchenPassage {

    Schedule schedule;
    Chef chef;
    Order order;
    OrderState orderState;
    @Given("an available chef and a PENDING state order")
    public void an_available_chef_and_a_pending_state_order() {
        schedule = new Schedule();
        chef = new Chef(schedule);
        order = new Order(null, OrderState.PENDING);
    }
    @When("order state is PENDING")
    public void order_state_is_pending() {
        assertEquals("PENDING", order.getOrderState());
    }
    @Then("the schedule associates the chef to the PENDING state order, which now is in the IN_PROGRESS state")
    public void the_schedule_associate_the_chef_to_the_pending_state_order_which_now_is_in_the_in_progress_state() {
        assertEquals("PENDING", order.getOrderState());
        schedule.associateOrder(chef, order);
        assertNotNull(chef.getOrderToPrepare());
        assertEquals("IN_PROGRESS", order.getOrderState());
    }


    @Given("an unavailable chef and a READY state order, and a possible other unavailable chef")
    public void an_unavailable_chef_and_a_ready_state_order_and_a_possible_other_unavailable_chef() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the order state is READY")
    public void the_order_state_is_ready() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the chef associated to this command can clean, help or take a break")
    public void the_chef_associated_to_this_command_can_clean_help_or_take_a_break() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
