package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Topping;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddCookieCartStepDef {

    User user;
    @Given("a user his cart and a cookie")
    public void a_user_his_cart_and_a_cookie() {
        // Write code here that turns the phrase above into concrete actions
        user = new User();
    }
    @When("he add cookie to his cart")
    public void he_add_cookie_to_his_cart() {
        Cookie  cookie = new Cookie("testCookie", new Dough(),new Flavour(),new ArrayList<Topping>());
        user.chooseCookies(cookie, 2);
    }
    @Then("he has one cookie in his cart")
    public void he_has_one_cookie_in_his_cart() {
        assertEquals(1, user.getCart().getItemList().size());
    }

}
