package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.ChefState;
import fr.unice.polytech.cod.store.Store;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class ManageTheKitchenPassageAndOrders {
    Schedule schedule;
    Chef chef;
    Order order;
    Store store;
    @Given("an chef who is \"([^\"]*)\"$")
    public void an_chef_who_is(ChefState chefState) {
        schedule = new Schedule();
        chef = new Chef(schedule);
        chef.setState(chefState);
        store=new Store("Magasin");
    }
    @And("An order at the state \"([^\"]*)\"$")
    public void an_order_at_the_state(OrderState orderState) {
        order = new Order(null, orderState, new User());
        if(orderState == OrderState.READY) chef.associateOrder(order);
    }

    @When("the chef is associate with an order")
    public void the_chef_is_associate_with_an_order() {
        store.associateOrder(chef, order);
    }

    @When("he give the order")
    public void he_give_the_order() {
        try {
            chef.giveOrder();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the state of the order is \"([^\"]*)\"$")
    public void the_state_of_the_order_is(OrderState orderState) {
        assertEquals(orderState, order.getOrderState());
    }
}
