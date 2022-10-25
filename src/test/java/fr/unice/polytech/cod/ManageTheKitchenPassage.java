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
    User user;
    @Given("an available chef and a PENDING state order")
    public void an_available_chef_and_a_pending_state_order() {
        schedule = new Schedule();
        user = new User();
        chef = new Chef(schedule);
        order = new Order(null, OrderState.PENDING, user);
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


    @Given("an unavailable chef")
    public void an_unavailable_chef() {
        schedule = new Schedule();
        chef = new Chef(schedule);
    }
    @Given("his ready order")
    public void his_ready_order() {
        order = new Order(null, OrderState.READY, user);
        chef.associateOrder(order);
    }
    @When("he give the order")
    public void he_give_the_order() {
        try {
            chef.giveOrder();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Then("he can clean, help or take a break")
    public void he_can_clean_help_or_take_a_break() {
        chef.makeOtherActivityDuringLeftTime();
    }
}
