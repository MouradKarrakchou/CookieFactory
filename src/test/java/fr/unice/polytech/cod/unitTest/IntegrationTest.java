package fr.unice.polytech.cod.unitTest;

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
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTest {
    /*

    private Store store;
    private User user;


    @Test
    void orderingTest() throws InvalidStoreException {
        user = new User(new Cart(), StoreLocation.instance);
        user.subscribeToFidelityAccount("Kalem", "kamel@gmail.coml", "123456");

        // Check if the store has been associated
        store = user.selectStore("Antibes");
        assertEquals("Antibes", user.getStore().getName());

        // Associating an ingredient to a tax value in a hashmap
        Map<Ingredient, Double> taxesValues = new HashMap<>();
        for(Ingredient ingredient : IngredientCatalog.instance.getIngredientList())
            taxesValues.put(ingredient, 3.0);

        // Fill the store's stock 20 times
        for (int i = 0; i < 20; i++) {
            store.fillStock(List.of(IngredientCatalog.instance.getDough("chocolate"),
                    IngredientCatalog.instance.getFlavour("chili"),
                    IngredientCatalog.instance.getTopping("milk chocolate"),
                    IngredientCatalog.instance.getTopping("M&M’s"),

                    IngredientCatalog.instance.getDough("plain"),
                    IngredientCatalog.instance.getFlavour("vanilla"),
                    IngredientCatalog.instance.getTopping("milk chocolate"),
                    IngredientCatalog.instance.getTopping("white chocolate")
            ), taxesValues);
        }

        // Check if the ingredients has been added to the store
        for(Ingredient ingredient : store.getStock().getIngredients()){
            if(ingredient.getName().equals("milk chocolate"))
                assertEquals(40, ingredient.getQuantity());
            else
                assertEquals(20, ingredient.getQuantity());
        }

        // Check if the store is able to craft the given cookies based on their name & the acctual stock
        assertEquals(1, user.viewCatalog().stream()
                .filter(cookie -> cookie.getName().equals("Cookie au chocolat"))
                .count());
        assertEquals(1, user.viewCatalog().stream()
                .filter(cookie -> cookie.getName().equals("Cookie à la vanille"))
                .count());

        // Check if the user can select the given amount of cookie
        assertTrue(user.chooseCookies(store.getCookieBook().getCookie("Cookie au chocolat"), 5));
        assertTrue(user.chooseCookies(store.getCookieBook().getCookie("Cookie au chocolat"), 15));
        assertTrue(user.chooseCookies(store.getCookieBook().getCookie("Cookie à la vanille"), 19));

        assertFalse(user.chooseCookies(store.getCookieBook().getCookie("Cookie au chocolat"), 1));
        assertFalse(user.chooseCookies(store.getCookieBook().getCookie("Cookie à la vanille"), 2));

        try {
            assertEquals(20, user.getCart().findItem("Cookie au chocolat").getQuantity());
            assertEquals(19, user.getCart().findItem("Cookie à la vanille").getQuantity());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        user.recapCart();

        TimeSlot timeSlot=new TimeSlot(new TimeClock(8,0),new TimeClock(8,15));
        List<TimeSlot> timeSlots=new ArrayList<>();
        timeSlots.add(timeSlot);
        user.getCart().setInterval(new Interval(timeSlots));

        Bill userBill = null;

        try {
            userBill = user.validateCart();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertFalse(userBill == null);  //TODO bug "méthode getDiscount() dans Order" quand on fait userBill.toString();


        aRandom.recapCart();

        Display.displayCookies(aRandom.viewCatalog());

    }
    */

}
