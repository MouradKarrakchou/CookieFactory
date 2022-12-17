package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.components.CartHandler;
import fr.unice.polytech.cod.components.CatalogComponent;
import fr.unice.polytech.cod.components.CookieBookComponent;
import fr.unice.polytech.cod.interfaces.StockModifier;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import fr.unice.polytech.cod.interfaces.UserAction;
import fr.unice.polytech.cod.interfaces.UserRequest;
import fr.unice.polytech.cod.pojo.CookieBook;
import fr.unice.polytech.cod.pojo.IngredientCatalog;
import fr.unice.polytech.cod.pojo.StoreLocation;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.schedule.TimeSlot;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import io.cucumber.java.bs.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class IntegrationTest {


    private Store store;
    private User user;

    @Autowired
    UserAction userAction;
    @Autowired
    UserRequest userRequest;
    @Autowired
    StockModifier stockModifier;
    IngredientCatalog ingredientCatalog = IngredientCatalog.instance;
    @Autowired
    CatalogComponent catalogComponent;
    @Autowired
    CookieBookComponent cookieBookComponent;
    @Autowired
    CartHandler cartHandler;


    @Test
    void orderingTest() throws InvalidStoreException {
        user = new User();
        userAction.subscribeToFidelityAccount(this.user, "Kamel", "kamel@gmail.coml", "123456");

        // Check if the store has been associated
        userAction.selectStore("Antibes", user.getCart());
        assertEquals("Antibes", user.getCart().getStore());

        this.store = user.getCart().getStore();

        // Associating an ingredient to a tax value in a hashmap
        Map<Ingredient, Double> taxesValues = new HashMap<>();
        for (Ingredient ingredient : IngredientCatalog.instance.getIngredientList())
            taxesValues.put(ingredient, 3.0);

        // Fill the store's stock 20 times
        for (int i = 0; i < 20; i++) {
            stockModifier.addIngredients(this.store.getStock(),
                    List.of(catalogComponent.getDough(ingredientCatalog, "chocolate"),
                            catalogComponent.getFlavour(ingredientCatalog, "chili"),
                            catalogComponent.getTopping(ingredientCatalog, "milk chocolate"),
                            catalogComponent.getTopping(ingredientCatalog, "M&M’s"),

                            catalogComponent.getDough(ingredientCatalog, "plain"),
                            catalogComponent.getFlavour(ingredientCatalog, "vanilla"),
                            catalogComponent.getTopping(ingredientCatalog, "milk chocolate"),
                            catalogComponent.getTopping(ingredientCatalog, "white chocolate")
                    ));
        }

        // Check if the ingredients has been added to the store
        for (Ingredient ingredient : store.getStock().getIngredients()) {
            if (ingredient.getName().equals("milk chocolate"))
                assertEquals(40, ingredient.getQuantity());
            else
                assertEquals(20, ingredient.getQuantity());
        }

        // Check if the store is able to craft the given cookies based on their name & the acctual stock
        assertEquals(1, userRequest.viewCatalog(this.store).stream()
                .filter(cookie -> cookie.getName().equals("Cookie au chocolat"))
                .count());
        assertEquals(1, userRequest.viewCatalog(this.store).stream()
                .filter(cookie -> cookie.getName().equals("Cookie à la vanille"))
                .count());

        // Check if the user can select the given amount of cookie
        assertTrue(userAction.addCookies(cookieBookComponent.getCookie(store.getCookieBook(), "Cookie au chocolat"), 5, user.getCart()));
        assertTrue(userAction.addCookies(cookieBookComponent.getCookie(store.getCookieBook(), "Cookie au chocolat"), 15, user.getCart()));
        assertTrue(userAction.addCookies(cookieBookComponent.getCookie(store.getCookieBook(), "Cookie à la vanille"), 19, user.getCart()));

        assertTrue(userAction.addCookies(cookieBookComponent.getCookie(store.getCookieBook(), "Cookie au chocolat"), 1, user.getCart()));
        assertTrue(userAction.addCookies(cookieBookComponent.getCookie(store.getCookieBook(), "Cookie à la vanille"), 2, user.getCart()));


        try {
            assertEquals(20, cartHandler.findItem(user.getCart(), "Cookie au chocolat").getQuantity());
            assertEquals(20, cartHandler.findItem(user.getCart(), "Cookie à la vanille").getQuantity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        userRequest.recapCart(this.user.getCart());


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
