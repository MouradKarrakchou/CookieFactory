package fr.unice.polytech.cod;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class clientCumcumber {

    CookieBook cookieBook = new CookieBook();
    Cart cart = new Cart(null);
    FidelityAccount fidelityAccount = new FidelityAccount();
    User user;


    @Given("a client")
    public void givenAClient()
    {
        user = new User(cookieBook, cart, fidelityAccount);
    }

    @When("requests the cookie list")
    public void whenRequestTheCookieList()
    {
        user = new User(cookieBook, cart, fidelityAccount);
    }

    @Then("he receive the entire list")
    public void thenHeReceiveTheEntireList()
    {
        user = new User(cookieBook, cart, fidelityAccount);
    }


}
