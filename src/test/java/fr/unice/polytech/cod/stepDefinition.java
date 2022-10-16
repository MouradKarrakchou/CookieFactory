package fr.unice.polytech.cod;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

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
        cookieList = user.seeCatalogue();
    }
    @Then("he receive the entire list")
    public void he_receive_the_entire_list() {
        System.out.println(cookieList);
    }
}
