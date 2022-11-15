package unitTest;

import fr.unice.polytech.cod.data.IngredientCatalog;
import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegrationTest {


    @Test
    void commande(){
        Store antibesStore;

        // Initializing the display
        Display.start();

        User aRandom = new User(new Cart(), StoreLocation.intance);

        aRandom.subscribeToFidelityAccount("Kalem", "kamel@gmail.coml", "123456");

        try {
            antibesStore = aRandom.selectStore("Antibes");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Display.displayCookies(aRandom.viewCatalog());


        Map<Ingredient, Double> taxesValues = new HashMap<>();
        for(Ingredient ingredient : IngredientCatalog.instance.getIngredientList())
            taxesValues.put(ingredient, 3.0);

        //Apres avoir refill
        for (int i = 0; i < 20; i++) {
            antibesStore.fillStock(List.of(IngredientCatalog.instance.getDough("chocolate"),
                    IngredientCatalog.instance.getFlavour("chili"),
                    IngredientCatalog.instance.getTopping("milk chocolate"),
                    IngredientCatalog.instance.getTopping("M&M’s"),

                    IngredientCatalog.instance.getDough("plain"),
                    IngredientCatalog.instance.getFlavour("vanilla"),
                    IngredientCatalog.instance.getTopping("milk chocolate"),
                    IngredientCatalog.instance.getTopping("white chocolate")
            ), taxesValues);
        }

        Display.displayCookies(aRandom.viewCatalog());

        aRandom.chooseCookies(antibesStore.getCookieBook().getCookie("Cookie au chocolat"), 20);
        aRandom.chooseCookies(antibesStore.getCookieBook().getCookie("Cookie à la vanille"), 40);

        aRandom.recapCart();

        Display.displayCookies(aRandom.viewCatalog());
    }

}
