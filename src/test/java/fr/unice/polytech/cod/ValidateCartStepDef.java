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

    @When("he can't validate his cart")
    public void he_can_t_validate_his_cart() {
        try {
            user.validateCart();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Then("he can finalise his order")
    public void he_can_finalise_his_order(){
        try {
            assertTrue(user.finaliseOrder()!=null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("he can't finalise his order")
    public void he_can_t_finalise_his_order() {
        boolean checkException = false;
        try {
            user.finaliseOrder();
        } catch (Exception e) {
            checkException = true;
        }
        assertTrue(checkException);

    }
}
