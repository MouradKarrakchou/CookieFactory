package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.food.*;
import fr.unice.polytech.cod.food.ingredient.*;
import fr.unice.polytech.cod.interfaces.StockModifier;
import fr.unice.polytech.cod.interfaces.StoreAccessor;
import fr.unice.polytech.cod.interfaces.StoreModifier;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.pojo.Stock;
import fr.unice.polytech.cod.store.Store;

import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillTest {
    Store store = new Store("StoRe");
    User user = new User( null, null,null,null); // Added null caused compile error
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

    @Test
    void showBillTest() {
        taxes.put(dough, 0.5);
        taxes.put(flavour, 0.6);
        taxes.put(topping1, 0.8);
        taxes.put(topping2, 0.9);

        ingredients.add(new Dough("Pâte", 0.5, 1000000));
        ingredients.add(new Flavour("Chocolat", 1.2, 1000000));
        ingredients.add(new Topping("Pépites", 2, 1000000));
        ingredients.add(new Topping("Crème", 1.5, 1000000));

        for (Ingredient ingredient:ingredients){
            stockModifier.addIngredient(store.getStock(),ingredient.clone());
            storeModifier.setTax(store,ingredient.clone(),0.5);
        }

        stockModifier.addIngredient(store.getStock(),dough);
        storeModifier.setTax(store,dough.clone(),0.5);
        Stock stock = store.getStock();
        cart.setStore(store);

        stock.addStock(new Dough("Pâte", 0.5, 1000000));
        stock.addStock(new Flavour("Chocolat", 1.2, 1000000));
        stock.addStock(new Topping("Pépites", 2, 1000000));
        stock.addStock(new Topping("Crème", 1.5, 1000000));

        Cookie cookie = new Cookie("CooKie", dough, flavour, new ArrayList<>(), new Mix(Mix.MixState.MIXED),new Cooking(Cooking.CookingState.CRUNCHY),10);
        Item item = new Item(cookie, 1);
        cart.addToCart(item);
        String receipt = """
                ===============StoRe===============
                CooKie..........123.1€
                ===================================
                Total price..........123.1€
                """;
        assertEquals(receipt, bill.toString());

        toppings.add(topping1);
        toppings.add(topping2);

        cookie = new Cookie("CooKYZ", dough, flavour, toppings, new Mix(Mix.MixState.MIXED),new Cooking(Cooking.CookingState.CRUNCHY),10);
        item = new Item(cookie, 1);
        cart.addToCart(item);
        receipt = """
                ===============StoRe===============
                CooKie..........123.1€
                CooKYZ..........214.8€
                ===================================
                Total price..........337.9€
                """;
        assertEquals(receipt, bill.toString());
    }
}
