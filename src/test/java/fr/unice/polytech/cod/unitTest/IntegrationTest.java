package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.components.CartHandler;
import fr.unice.polytech.cod.components.CatalogExplorer;
import fr.unice.polytech.cod.components.CookieBookManager;
import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.FidelityAccountAlreadyExistException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.pojo.ingredient.Cooking;
import fr.unice.polytech.cod.pojo.ingredient.Mix;
import fr.unice.polytech.cod.interfaces.StockModifier;
import fr.unice.polytech.cod.interfaces.UserAction;
import fr.unice.polytech.cod.interfaces.UserRequest;
import fr.unice.polytech.cod.pojo.CookieBook;
import fr.unice.polytech.cod.pojo.IngredientCatalog;
import fr.unice.polytech.cod.pojo.ingredient.Ingredient;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.pojo.order.Bill;
import fr.unice.polytech.cod.pojo.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.pojo.schedule.TimeSlot;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class IntegrationTest {

    @Autowired
    UserAction userAction;
    @Autowired
    UserRequest userRequest;
    @Autowired
    StockModifier stockModifier;
    IngredientCatalog ingredientCatalog = IngredientCatalog.instance;
    @Autowired
    CatalogExplorer catalogExplorer;
    @Autowired
    CookieBookManager cookieBookManager;
    @Autowired
    CartHandler cartHandler;


    @Test
    void orderingTest() throws InvalidStoreException, NotMatchingCatalogRequirementException, CookieAlreadyExistingException, FidelityAccountAlreadyExistException {
        User user = new User();
        userAction.subscribeToFidelityAccount(user, "Kamel", "kamel@gmail.coml", "123456");

        // Check if the store has been associated
        userAction.selectStore("Antibes", user.getCart());
        assertEquals("Antibes", user.getCart().getStore().getName());

        Store store = user.getCart().getStore();
        store.getStock().getIngredients().clear();

        //initialize CookieBook
        CookieBook cookieBook = new CookieBook();
        cookieBookManager.addCookieRecipe(cookieBook,
                new Cookie("Cookie à la vanille",
                        catalogExplorer.getDough(ingredientCatalog,"plain"),
                        catalogExplorer.getFlavour(ingredientCatalog,"vanilla"),
                        List.of(catalogExplorer.getTopping(ingredientCatalog,"milk chocolate"),catalogExplorer.getTopping(ingredientCatalog,"white chocolate")),
                        new Mix(Mix.MixState.MIXED),
                        new Cooking(Cooking.CookingState.CHEWY),
                        5)
        );
        cookieBookManager.addCookieRecipe(cookieBook,
                new Cookie("Cookie au chocolat",
                        catalogExplorer.getDough(ingredientCatalog,"pate"),
                        catalogExplorer.getFlavour(ingredientCatalog,"chili"),
                        List.of(catalogExplorer.getTopping(ingredientCatalog,"milk chocolate"),catalogExplorer.getTopping(ingredientCatalog,"M&M’s")),
                        new Mix(Mix.MixState.MIXED),
                        new Cooking(Cooking.CookingState.CHEWY),
                        10)
        );
        cookieBookManager.addCookieRecipe(cookieBook,
                new Cookie("Cookie à la pistache",
                        catalogExplorer.getDough(ingredientCatalog,"peanut butter"),
                        catalogExplorer.getFlavour(ingredientCatalog,"chili"),
                        List.of(catalogExplorer.getTopping(ingredientCatalog,"milk chocolate")),
                        new Mix(Mix.MixState.MIXED),
                        new Cooking(Cooking.CookingState.CHEWY),
                        10)
        );

        // Fill the store's stock 20 times
        for (int i = 0; i < 20; i++) {
            stockModifier.addIngredients(store.getStock(),
                    List.of(catalogExplorer.getDough(ingredientCatalog, "pate"),
                            catalogExplorer.getFlavour(ingredientCatalog, "chili"),
                            catalogExplorer.getTopping(ingredientCatalog, "milk chocolate"),
                            catalogExplorer.getTopping(ingredientCatalog, "M&M’s"),

                            catalogExplorer.getDough(ingredientCatalog, "plain"),
                            catalogExplorer.getFlavour(ingredientCatalog, "vanilla"),
                            catalogExplorer.getTopping(ingredientCatalog, "milk chocolate"),
                            catalogExplorer.getTopping(ingredientCatalog, "white chocolate")
                    ));
        }

        //set to the store the CookieBook previously initialized
        store.setCookieBook(cookieBook);

        // Check if the ingredients has been added to the store
        for (Ingredient ingredient : store.getStock().getIngredients()) {
            if (ingredient.getName().equals("milk chocolate"))
                assertEquals(40, ingredient.getQuantity());
            else
                assertEquals(20, ingredient.getQuantity());
        }

        // Check if the store is able to craft the given cookies based on their name & the actual stock
        assertEquals(1, userRequest.viewCatalog(store).stream()
                .filter(cookie -> cookie.getName().equals("Cookie au chocolat"))
                .count());
        assertEquals(1, userRequest.viewCatalog(store).stream()
                .filter(cookie -> cookie.getName().equals("Cookie à la vanille"))
                .count());

        // Check if the user can select the given amount of cookie
        assertTrue(userAction.addCookies(cookieBookManager.getCookie(store.getCookieBook(), "Cookie au chocolat"), 5, user.getCart()));
        assertTrue(userAction.addCookies(cookieBookManager.getCookie(store.getCookieBook(), "Cookie au chocolat"), 15, user.getCart()));
        assertTrue(userAction.addCookies(cookieBookManager.getCookie(store.getCookieBook(), "Cookie à la vanille"), 19, user.getCart()));

        assertFalse(userAction.addCookies(cookieBookManager.getCookie(store.getCookieBook(), "Cookie au chocolat"), 1, user.getCart()));
        assertFalse(userAction.addCookies(cookieBookManager.getCookie(store.getCookieBook(), "Cookie à la vanille"), 2, user.getCart()));


        try {
            assertEquals(20, cartHandler.findItem(user.getCart(), "Cookie au chocolat").getQuantity());
            assertEquals(19, cartHandler.findItem(user.getCart(), "Cookie à la vanille").getQuantity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        userRequest.recapCart(user.getCart());


        TimeSlot timeSlot = new TimeSlot(new TimeClock(8, 0), new TimeClock(8, 15));
        List<TimeSlot> timeSlots = new ArrayList<>();
        timeSlots.add(timeSlot);
        user.getCart().setInterval(new Interval(timeSlots));

        Bill userBill = null;

        try {
            userBill = userAction.validateCart(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertFalse(userBill == null);  //TODO bug "méthode getDiscount() dans Order" quand on fait userBill.toString();

        /*
        aRandom.recapCart();

        Display.displayCookies(aRandom.viewCatalog());

         */

    }


}
