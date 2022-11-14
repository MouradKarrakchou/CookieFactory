package fr.unice.polytech.cod;

import fr.unice.polytech.cod.data.CookieBook;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Schedule;
import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.ChefState;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.data.StoreManager;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class ManageTheKitchenPassageAndOrders {
    Schedule schedule;
    Chef chef;
    Order order;
    Store store;
    Bill bill;

    @Given("an chef who is \"([^\"]*)\"$")
    public void an_chef_who_is(ChefState chefState) {
        store=new Store("Magasin");
        chef = new Chef(store);
        chef.setState(chefState);
    }
    @And("An order at the state \"([^\"]*)\"$")
    public void an_order_at_the_state(OrderState orderState) {
        order = new Order(null, orderState, new User(new CookieBook(),new Cart(),new StoreManager()));
        if(orderState == OrderState.READY) chef.associateOrder(order);
    }

    @When("the chef is associate with an order")
    public void the_chef_is_associate_with_an_order() {
        store.associateOrder(chef, order);
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
    public void client_retrieve_his_order() throws  Exception{
        this.store.retrieveOrder(bill);
    }
}
