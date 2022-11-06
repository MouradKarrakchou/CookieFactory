package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.Topping;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.Store;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidateCartStepDef {
    User user;
    Store store;
    Cart cart;


    @Given("a user with a non-empty cart")
    public void a_user_with_a_non_empty_cart() {
        CookieBook cookieBook = new CookieBook();
        cart = new Cart();
        user = new User(cookieBook, cart, new FidelityAccount());
        cart.getStore().getStock().addStock(new Dough("Pate verte",25,50));
        cart.getStore().getStock().addStock(new Flavour("Vert",25,50));

        Cookie  cookie = new Cookie("testCookie",  new Dough("Pate verte",25,50),new Flavour("Vert",25,50),new ArrayList<Topping>());
        user.chooseCookies(cookie, 1);
    }

    @When("he validate his cart")
    public void he_validate_his_cart() throws Exception {
        user.validateCart();
    }


    @Then("his order is created")
    public void his_order_is_created(){
        assertEquals(1, user.getOrders().size());
        assertEquals(1, store.getOrderList().size());
        Stock stock = cart.getStore().getStock();
        Optional<Ingredient> ingredient = stock.findIngredientInStock(new Dough("Pate verte", 25, 30));
        assertEquals(0, ingredient.get().getQuantity());
    }

}
