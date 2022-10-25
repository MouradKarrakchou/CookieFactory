package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Topping;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateCartStepDef {
    User user;

    @Given("a user with a non-empty cart")
    public void a_user_with_a_non_empty_cart() {
        user = new User();
        Cookie  cookie = new Cookie("testCookie",  new Dough("Pate verte",25,50),new Flavour("Vert",25,50),new ArrayList<Topping>());
        user.chooseCookies(cookie, 1);
    }

    @Given("a user with a empty cart")
    public void a_user_with_a_empty_cart() {
        user = new User();
    }

    @When("he validate his cart")
    public void he_validate_his_cart() throws Exception {
        user.validateCart();
    }


    @Then("his order is created")
    public void his_order_is_created(){
        assertEquals(1, user.getOrders().size());
        assertEquals(1, user.getOrders().size());

    }

}
