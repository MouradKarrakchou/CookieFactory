package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.Topping;
import fr.unice.polytech.cod.store.InvalidStoreExepection;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.StoreManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CartManagementStepDef {

    User user;
    Cart cart;
    Cookie testCookie;
    List<Cookie> cookieList;
    Exception exception;
    TimeSlot timeSlot;

    @Given("a user")
    public void a_user() {
        user = new User(new CookieBook(),new Cart(),new StoreManager());
    }

    @Given("a valid time slot")
    public void a_valid_time_slot() {
        timeSlot=new TimeSlot(null,null);
        cart.setTimeSlot(timeSlot);
    }

    @Given("a store named {string}")
    public void the_antibes_store(String name) throws InvalidStoreExepection {
        user.selectStore(name);
        user.getStore().getStock().addStock(new Dough("Pate verte",25,100));
        user.getStore().getStock().addStock(new Flavour("Vert",25,100));
    }


    @And("a non-empty cart")
    public void a_non_empty_cart() {
        cart = user.getCart();
        cart.getItemList().add(new Item(testCookie, 2));
        cart.setTimeSlot(new TimeSlot(null,null));
    }
    @And("a non-empty cart with one cookie")
    public void a_non_empty_cart_with_one_cookie() {
        cart = user.getCart();
        cart.getItemList().add(new Item(testCookie, 1));
        cart.setTimeSlot(new TimeSlot(null,null));
    }
    @And("a valid cookie")
    public void a_valid_cookie() {
        testCookie =new Cookie("testCookie", new Dough("Pate verte",25,50),new Flavour("Vert",25,50),new ArrayList<Topping>());
    }


    @When("he add cookie to his cart")
    public void he_add_cookie_to_his_cart() {
        user.chooseCookies(testCookie, 1);
    }
    @When("he remove a cookie from his cart")
    public void he_remove_a_cookie_from_his_cart() throws Exception {
        List<Item> allItems =  user.getAllItemsFromCart();
        Item item = user.getItemFromCart("testCookie");
        user.removeOneItemFromCart(item);
    }
    @When("he requests the cookie list")
    public void he_requests_the_cookie_list() {
        // Write code here that turns the phrase above into concrete actions
        cookieList = user.viewCatalog();
    }
    @When("he validate his cart")
    public void he_validate_his_cart() throws Exception {
        user.validateCart();
    }

    @When("we choose a valid store")
    public void we_choose_a_valid_store() throws InvalidStoreExepection {
        user.selectStore("Antibes");
    }


    @When("we choose an invalid store")
    public void we_choose_an_invalid_store() {
        try {
            user.selectStore("invalidStore");
        } catch (InvalidStoreExepection e) {
            this.exception=e;
        }
    }
    @When("he subscribe to the fidelity program as {string} with {string} mail and this password {string}")
    public void he_subscribe_to_the_fidelity_program(String name, String email, String password) {
        user.subscribeToFidelityAccount(name, email, password);
    }

    @When("the user validate his order")
    public void the_user_validate_his_order() throws InvalidStoreExepection {
        System.out.println("TODO");
    }

    @Then("the bill is created")
    public void the_bill_is_created() throws InvalidStoreExepection {
        System.out.println("TODO");
    }

    @Then("he take advantage of our loyalty program")
    public void he_take_advantage_of_our_loyalty_program() {
        assertTrue(user.getSubscription().isPresent());
    }
    @When("a user chooses a time slot")
    public void a_user_chooses_a_time_slot() {
        this.user.chooseTimeSlot(timeSlot);
    }
    @When("he validate the cart")
    public void he_validate_the_cart() throws Exception {
        this.cart.validate(user);
    }

    @Then("the order is associated with the time slot")
    public void the_order_is_associated_with_the_time_slot() throws InvalidStoreExepection {
        Store store=this.user.getStoreManager().selectStore("Antibes");
        assertEquals(1,store.getOrderList().size());
        assertTrue(this.timeSlot.order.isPresent());
        assertTrue(this.timeSlot.reserved);
    }
    @Then("the order is reserverd")
    public void the_order_is_reserverd() {
        assertTrue(this.timeSlot.reserved);
    }

    @Then("an InvalidStoreException is triggered")
    public void an_invalid_store_exception_is_triggered() {
        assertTrue(this.exception!=null);
        if (this.exception!=null)
            assertTrue(this.exception instanceof InvalidStoreExepection);
    }



    @Then("the right store is selected in the cart")
    public void the_right_store_is_selected_in_the_cart() {
        Assertions.assertTrue(user.getCart().getStore().getName().equals("Antibes"));
    }


    @Then("his order is created")
    public void his_order_is_created(){
        Assertions.assertEquals(1, user.getOrders().size());
        Assertions.assertEquals(1, cart.getStore().getOrderList().size());
        Stock stock = cart.getStore().getStock();
        Optional<Ingredient> ingredient = stock.findIngredientInStock(new Dough("Pate verte", 25, 30));
        Assertions.assertEquals(0, ingredient.get().getQuantity());
    }
    @Then("he receive the entire list")
    public void he_receive_the_entire_list() {
        assertFalse(cookieList.isEmpty());
    }

    @Then("a cookie is added to his cart")
    public void a_cookie_is_added_to_his_cart() {
        assertEquals(2, user.getCart().getItemList().size());
    }

    @Then("his cart has one item less")
    public void his_cart_has_one_item_less() {
       assertEquals(1, cart.getItemQuantity("testCookie"));
    }

}
