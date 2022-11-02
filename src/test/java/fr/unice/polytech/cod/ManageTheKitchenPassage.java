package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.ChefState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class ManageTheKitchenPassage {
    Schedule schedule;
    Chef chef;
    Order order;
    @Given("an chef who is \"([^\"]*)\"$")
    public void an_chef_who_is(ChefState chefState) {
        schedule = new Schedule();
        chef = new Chef(schedule);
        chef.setState(chefState);
    }
    @And("An order at the state \"([^\"]*)\"$")
    public void an_order_at_the_state(OrderState orderState) {
        order = new Order(null, orderState, new User());
    }
    @And("his ready order")
    public void his_ready_order() {
        order = new Order(null, OrderState.READY, new User());
        chef.associateOrder(order);
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
