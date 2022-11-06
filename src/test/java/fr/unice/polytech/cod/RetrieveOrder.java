package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Chef;
import fr.unice.polytech.cod.store.Store;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

import static org.junit.Assert.*;

public class RetrieveOrder {

    Store store;

    Chef chef;

    User user;

    Order order;

    @Given("a chef, a client, a client order, a store and the order list of a store including that of the client who is READY")
    public void a_chef_a_client_a_client_order_a_store_and_the_order_list_of_a_store_including_that_of_the_client_who_is_ready() {
        store = new Store(null);
        user = new User();
        chef = new Chef(null);
        order = new Order(null, OrderState.READY, user);
        store.setOrderList(List.of(order, new Order(null, OrderState.READY, user)));
        user.addOrder(order);
    }

    @When("a chef informs the system that he has given the order")
    public void a_chef_informs_the_system_that_he_has_given_the_order() {
        this.store.retrieveOrder(order);
    }

    @Then("the order is removed from the customer's order list and current orders, added to its history and its status changes to RETRIEVE")
    public void the_order_is_removed_from_the_customer_s_order_list_and_current_orders_added_to_its_history_and_its_status_changes_to_retrieve() {
        assertFalse(this.store.getOrderList().contains(order));
        assertEquals(1, this.store.getOrderList().size());
        assertEquals(1, this.store.getOrderList().size());

        assertFalse(this.user.getOrders().contains(order));
        assertTrue(this.user.getUserOrdersHistory().contains(order));
    }

}
