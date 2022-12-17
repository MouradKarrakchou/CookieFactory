package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.*;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.pojo.ingredient.*;
import fr.unice.polytech.cod.pojo.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.IngredientCatalog;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.pojo.store.Store;

import fr.unice.polytech.cod.pojo.user.Cart;
import fr.unice.polytech.cod.pojo.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BillTest {
    Store store = new Store("StoRe");
    User user = new User(); // Added null caused compile error
    Cart cart = new Cart();
    Order order = new Order(cart, user);
    Bill bill = new Bill(order);

    Dough dough = new Dough("Pâte", 0.5, 1);
    Flavour flavour = new Flavour("Chocolat", 1.2, 1);
    Topping topping1 = new Topping("Pépites", 2, 1);
    Topping topping2 = new Topping("Crème", 1.5, 1);
    List<Topping> toppings = new ArrayList<>();
    @Autowired
    StoreModifier storeModifier;
    @Autowired
    StockModifier stockModifier;
    @Autowired
    CartActions cartActions;
    @Autowired
    BillAction billAction;
    @Autowired
    ICookieBookManager iCookieBookManager;
    @Autowired
    ICatalogExplorer iCatalogExplorer;
    @Autowired
    IngredientActions ingredientActions;
    IngredientCatalog ingredientCatalog = IngredientCatalog.instance;

    @Test
    void showBillTest() {
        cart.setStore(store);

        for (int i = 0; i < 20; i++) {
            stockModifier.addIngredients(this.store.getStock(),
                    List.of(iCatalogExplorer.getDough(ingredientCatalog, "pate"),
                            iCatalogExplorer.getFlavour(ingredientCatalog, "chocolate"),
                            iCatalogExplorer.getTopping(ingredientCatalog, "chocolate chips"),
                            iCatalogExplorer.getTopping(ingredientCatalog, "cream")
                    ));
        }


        Dough dough1 = iCatalogExplorer.getDough(ingredientCatalog, "pate");
        stockModifier.addIngredient(store.getStock(), dough1);

        Flavour flavour1 = iCatalogExplorer.getFlavour(ingredientCatalog, "chocolate");
        stockModifier.addIngredient(store.getStock(), flavour1);


        Topping topping1 = iCatalogExplorer.getTopping(ingredientCatalog, "chocolate chips");
        stockModifier.addIngredient(store.getStock(), topping1);

        Topping topping2 = iCatalogExplorer.getTopping(ingredientCatalog, "cream");
        stockModifier.addIngredient(store.getStock(), topping2);

        store.setTax(1);

        Cookie cookie = new Cookie("CooKie",
                dough1,
                flavour1,
                new ArrayList<>(List.of(topping1, topping2)),
                new Mix(Mix.MixState.MIXED),
                new Cooking(Cooking.CookingState.CRUNCHY),
                10);

        try {
            iCookieBookManager.addCookieRecipe(store.getCookieBook(), cookie);
        } catch (CookieAlreadyExistingException | NotMatchingCatalogRequirementException e) {
            throw new RuntimeException(e);
        }


        Item item = new Item(iCookieBookManager.getCookie(store.getCookieBook(), "CooKie"), 1);
        cartActions.addToCart(cart, item);
        String receipt = """
                ===============StoRe===============
                CooKie..........4.67€
                ===================================
                Total price..........4.67€
                """;
        assertEquals(receipt, billAction.returnBill((bill)));

        toppings.add(topping1);
        toppings.add(topping2);

        Cookie cookie2 = new Cookie("CooKYZ",
                dough1,
                flavour1,
                new ArrayList<>(List.of(topping1)),
                new Mix(Mix.MixState.MIXED),
                new Cooking(Cooking.CookingState.CRUNCHY),
                10);

        try {
            iCookieBookManager.addCookieRecipe(store.getCookieBook(), cookie2);
        } catch (CookieAlreadyExistingException | NotMatchingCatalogRequirementException e) {
            throw new RuntimeException(e);
        }

        item = new Item(cookie2, 5);
        cartActions.addToCart(cart, item);
        receipt = """
                ===============StoRe===============
                CooKie..........4.67€
                CooKYZ..........17.75€
                ===================================
                Total price..........22.42€
                """;
        assertEquals(receipt, billAction.returnBill((bill)));
    }
}
