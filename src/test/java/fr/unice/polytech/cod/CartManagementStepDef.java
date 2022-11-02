package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Topping;
import fr.unice.polytech.cod.store.Store;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartManagementStepDef {

    User user;
    Cart cart;
    Cookie testCookie;

    @Given("a user")
    public void a_user() {
        user = new User();
    }
    @And("a non-empty cart")
    public void a_non_empty_cart() {
        cart = user.getCart();
        cart.getItemList().add(new Item(testCookie, 2));
    }
    @And("a valid cookie")
    public void a_valid_cookie() {
        testCookie =new Cookie("testCookie", new Dough("Pate verte",25,50),new Flavour("Vert",25,50),new ArrayList<Topping>());
    }


    @When("he add cookie to his cart")
    public void he_add_cookie_to_his_cart() {
        user.chooseCookies(testCookie, 2);
    }
    @When("he remove a cookie from his cart")
    public void he_remove_a_cookie_from_his_cart() throws Exception {
        List<Item> allItems =  user.getAllItemsFromCart();
        Item item = user.getItemFromCart("testCookie");
        user.removeOneItemFromCart(item);
    }


    @Then("a cookie is added to his cart")
    public void a_cookie_is_added_to_his_cart() {
        assertEquals(1, user.getCart().getItemList().size());
    }

    @Then("his cart has one item less")
    public void his_cart_has_one_item_less() {
       assertEquals(1, cart.getItemQuantity("testCookie"));
    }

}
