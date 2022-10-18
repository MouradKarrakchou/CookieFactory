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
    @Given("a user his cart and a valid cookie")
    public void a_user_his_cart_and_a_valid_cookie() {
        user = new User();
    }
    @When("he add cookie to his cart")
    public void he_add_cookie_to_his_cart() {
        Cookie  cookie = new Cookie("testCookie", new Dough("Pate verte",25,50),new Flavour("Vert",25,50),new ArrayList<Topping>());
        user.chooseCookies(cookie, 2);
    }
    @Then("a cookie is added to his cart")
    public void a_cookie_is_added_to_his_cart() {
        assertEquals(1, user.getCart().getItemList().size());
    }


}
