package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.food.*;
import fr.unice.polytech.cod.food.ingredient.*;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.store.Store;

import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class BillTest {
    Store store = new Store("StoRe");
    User user = new User(); // Added null caused compile error
    Cart cart = new Cart();
    Order order = new Order(cart, user);
    Bill bill = new Bill(order);

    Dough dough = new Dough("Pâte", 0.5, 100);
    Flavour flavour = new Flavour("Chocolat", 1.2, 60);
    Topping topping1 = new Topping("Pépites", 2, 30);
    Topping topping2 = new Topping("Crème", 1.5, 20);
    List<Topping> toppings = new ArrayList<>();
    @Autowired
    StoreModifier storeModifier;
    @Autowired
    StockModifier stockModifier;
    @Autowired
    CartActions cartActions;
    @Autowired
    BillAction billAction;

    @Test
    void showBillTest() {

        stockModifier.addIngredient(store.getStock(), new Dough("Pâte", 0.5, 1000000));
        storeModifier.setTax(store, "Pâte", 0.5);

        stockModifier.addIngredient(store.getStock(), new Flavour("Chocolat", 1.2, 1000000));
        storeModifier.setTax(store, "Chocolat", 0.6);

        stockModifier.addIngredient(store.getStock(), new Topping("Pépites", 2, 1000000));
        storeModifier.setTax(store, "Pépites", 0.8);

        stockModifier.addIngredient(store.getStock(), new Topping("Crème", 1.5, 1000000));
        storeModifier.setTax(store, "Crème", 0.9);

        cart.setStore(store);

        Cookie cookie = new Cookie("CooKie", dough, flavour, new ArrayList<>(), new Mix(Mix.MixState.MIXED), new Cooking(Cooking.CookingState.CRUNCHY), 10);
        Item item = new Item(cookie, 1);
        cartActions.addToCart(cart, item);
        String receipt = """
                ===============StoRe===============
                CooKie..........123.1€
                ===================================
                Total price..........123.1€
                """;
        assertEquals(receipt, billAction.returnBill((bill)));

        toppings.add(topping1);
        toppings.add(topping2);

        cookie = new Cookie("CooKYZ", dough, flavour, toppings, new Mix(Mix.MixState.MIXED), new Cooking(Cooking.CookingState.CRUNCHY), 10);
        item = new Item(cookie, 1);
        cartActions.addToCart(cart, item);
        receipt = """
                ===============StoRe===============
                CooKie..........123.1€
                CooKYZ..........214.8€
                ===================================
                Total price..........337.9€
                """;
        assertEquals(receipt, billAction.returnBill((bill)));
    }
}
