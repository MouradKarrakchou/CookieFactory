package unitTest;

import fr.unice.polytech.cod.data.IngredientCatalog;
import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.store.InvalidStoreException;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTest {
    private Store store;
    private User user;


    @Test
    void orderingTest() throws InvalidStoreException {
        user = new User(new Cart(), StoreLocation.intance);
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
        assertTrue(user.chooseCookies(store.getCookieBook().getCookie("Cookie à la vanille"), 20));

        assertTrue(user.chooseCookies(store.getCookieBook().getCookie("Cookie au chocolat"), 1));
        assertTrue(user.chooseCookies(store.getCookieBook().getCookie("Cookie à la vanille"), 1));
/*
        aRandom.recapCart();

        Display.displayCookies(aRandom.viewCatalog());*/
    }

}
