package fr.unice.polytech.cod.cucumber;

import fr.unice.polytech.cod.interfaces.ChefAction;
import fr.unice.polytech.cod.interfaces.OrderActions;
import fr.unice.polytech.cod.interfaces.OrderStatesAction;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Schedule;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.ChefState;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManageTheKitchenPassageAndOrders {
    Schedule schedule;
    Chef chef;
    Order order;
    Store store;
    Bill bill;
    @Autowired
    OrderActions orderActions;
    @Autowired
    OrderStatesAction orderStatesAction;
    @Autowired
    ChefAction chefAction;

    @Given("an chef who is \"([^\"]*)\"$")
    public void an_chef_who_is(ChefState chefState) {
        store = new Store("Magasin");
        chef = new Chef(store);
        chef.setState(chefState);
    }

    @And("An order at the state \"([^\"]*)\"$")
    public void an_order_at_the_state(OrderState orderState) {
        order = new Order(null, orderState, new User());
        if (orderState == OrderState.READY) chefAction.associateOrder(chef, order);
    }

    @When("the chef is associate with an order")
    public void the_chef_is_associate_with_an_order() {
        chefAction.associateOrder(chef, order);
    }

    @Then("the state of the order is \"([^\"]*)\"$")
    public void the_state_of_the_order_is(OrderState orderState) {
        assertEquals(orderState, order.getOrderState());
    }

    @Given("A store")
    public void a_store() {
        // Write code here that turns the phrase above into concrete actions
        store = new Store(null);
    }

    @Given("A order at the state \"([^\"]*)\"$")
    public void a_order_at_the_state(OrderState orderState) {
        order = new Order(null, OrderState.READY, null);
        store.setOrderList(List.of(order, new Order(null, OrderState.READY, null)));
        bill = new Bill(order);
    }

    @When("client retrieve his order")
    public void client_retrieve_his_order() throws Exception {
        orderStatesAction.retrieveOrder(this.store.getOrderList(), bill);
    }


    @And("the chef is asking for work")
    public void theChefIsAskingForWork() throws Exception {
        chefAction.startWork(chef);
    }

    @Then("the state of the chef should be \"([^\"]*)\"$")
    public void theStateOfTheChefShouldBe(ChefState chefState) {
        assertEquals(ChefState.UNAVAILABLE, chefState);
    }

    @When("the chef terminate his current order")
    public void theChefTerminateHisCurrentOrder() {
        chefAction.terminateCurrentOrder(chef);
    }
}
