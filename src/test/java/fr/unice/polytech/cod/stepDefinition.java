package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Topping;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class stepDefinition {
    User user;
    List<Cookie> cookieList;
    @Given("a user")
    public void a_user() {
        // Write code here that turns the phrase above into concrete actions
        user = new User();
    }
    @When("he requests the cookie list")
    public void he_requests_the_cookie_list() {
        // Write code here that turns the phrase above into concrete actions
        cookieList = user.getCatalogue();
    }
    @Then("he receive the entire list")
    public void he_receive_the_entire_list() {
        assertFalse(cookieList.isEmpty());
    }

    @When("he add cookie to his cart")
    public void he_add_cookie_to_his_cart() {
       Cookie  cookie = new Cookie("testCookie", new Dough(),new Flavour(),new ArrayList<Topping>());
        user.chooseCookies(cookie, 2);
    }
    @Then("he can watch the content of his cart")
    public void he_can_watch_the_content_of_his_cart() {
        assertEquals(1, user.getCart().getItemList().size());
    }
}
