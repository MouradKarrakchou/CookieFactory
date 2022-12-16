package fr.unice.polytech.cod.cucumber;

import fr.unice.polytech.cod.components.CookieBookComponent;
import fr.unice.polytech.cod.interfaces.BrandManagerActions;
import fr.unice.polytech.cod.interfaces.CatalogExplorer;
import fr.unice.polytech.cod.interfaces.UserAction;
import fr.unice.polytech.cod.pojo.CookieBook;
import fr.unice.polytech.cod.pojo.IngredientCatalog;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Cooking;
import fr.unice.polytech.cod.food.ingredient.Mix;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.user.Cart;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCookieBook {
    Cart cart;
    Cookie newCookie;
    CookieBook cookieBook;
    final IngredientCatalog ingredientCatalog = IngredientCatalog.instance;

    @Autowired
    CatalogExplorer catalogExplorer;

    @Autowired
    UserAction userAction;

    @Autowired
    CookieBookComponent cookieBookComponent;

    @Autowired
    BrandManagerActions brandManagerActions;

    @Given("a cookie")
    public void a_cookie() {
        newCookie = new Cookie("Cookie a la vanille",
                catalogExplorer.getDough(ingredientCatalog, "peanut butter"),
                catalogExplorer.getFlavour(ingredientCatalog, "vanilla"),
                List.of(catalogExplorer.getTopping(ingredientCatalog, "milk chocolate"), catalogExplorer.getTopping(ingredientCatalog, "M&M’s")),
                new Mix(Mix.MixState.MIXED),
                new Cooking(Cooking.CookingState.CHEWY),
                10);
    }

    @Given("a cookieBook")
    public void a_cookieBook() throws InvalidStoreException {
        cookieBook = userAction.selectStore("Antibes", cart).getCookieBook();
    }

    @When("when a brandManager add a cookie to the cookie book")
    public void when_a_brand_manager_add_a_cookie_to_the_cookie_book() throws Exception {
        brandManagerActions.validCookie(newCookie, "Antibes");
    }

    @Then("the cookkie is add to the cookie book")
    public void the_cookkie_is_add_to_the_cookie_book() {
        assertEquals(4, cookieBook.getCookies().size());
    }

    @When("when a brandManager remove a cookie to the cookie book")
    public void when_a_brand_manager_remove_a_cookie_to_the_cookie_book() throws Exception {
        brandManagerActions.removeCookie(cookieBookComponent.getCookie(cookieBook, "Cookie au chocolat"), "Antibes");
    }
    @Then("the cookkie is remove to the cookie book")
    public void the_cookkie_is_remove_to_the_cookie_book() {
        assertEquals(2, cookieBook.getCookies().size());
    }
}
