package fr.unice.polytech.cod.cucumber;

import fr.unice.polytech.cod.data.CookieBook;
import fr.unice.polytech.cod.data.IngredientCatalog;
import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Cooking;
import fr.unice.polytech.cod.food.ingredient.Mix;
import fr.unice.polytech.cod.store.BrandManager;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCookieBook {
    Cookie newCookie;
    BrandManager brandManager;
    CookieBook cookieBook;
    private StoreLocation storeLocation;
    private final IngredientCatalog ingredientCatalog = IngredientCatalog.instance;

    @Given("a cookie")
    public void a_cookie() {
        newCookie = new Cookie("Cookie a la vanille",
                ingredientCatalog.getDough("peanut butter"),
                ingredientCatalog.getFlavour("vanilla"),
                List.of(ingredientCatalog.getTopping("milk chocolate"), ingredientCatalog.getTopping("M&M’s")),
                new Mix(Mix.MixState.MIXED),
                new Cooking(Cooking.CookingState.CHEWY),
                10);
    }

    @Given("brandManager")
    public void brand_manager() {
        this.brandManager = new BrandManager(this.storeLocation);
    }

    @Given("a cookieBook")
    public void a_cookieBook() {
        cookieBook = storeLocation.findStore("Antibes").getCookieBook();
    }

    @When("when a brandManager add a cookie to the cookie book")
    public void when_a_brand_manager_add_a_cookie_to_the_cookie_book() throws Exception {
        brandManager.validCookie(newCookie, "Antibes");
    }

    @Then("the cookkie is add to the cookie book")
    public void the_cookkie_is_add_to_the_cookie_book() throws InvalidStoreException {
        assertEquals(4, cookieBook.getCookies().size());
    }

    @When("when a brandManager remove a cookie to the cookie book")
    public void when_a_brand_manager_remove_a_cookie_to_the_cookie_book() throws Exception {
        brandManager.removeCookie(cookieBook.getCookie("Cookie au chocolat"), "Antibes");
    }
    @Then("the cookkie is remove to the cookie book")
    public void the_cookkie_is_remove_to_the_cookie_book() {
        assertEquals(2, cookieBook.getCookies().size());
    }

    @Given("a storeLocattion")
    public void aStoreLocattion() {
        this.storeLocation = StoreLocation.getInstance();
    }
}
