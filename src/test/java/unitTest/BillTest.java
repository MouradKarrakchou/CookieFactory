package unitTest;

import fr.unice.polytech.cod.*;
import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Topping;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.Store;
import io.cucumber.java.cy_gb.A;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BillTest {
    Store store = new Store("StoRe");
    User user = new User();
    Cart cart = new Cart();
    Order order = new Order(cart, user);
    Bill bill = new Bill(order);

    Dough dough = new Dough("Pâte", 0.5, 100);
    Flavour flavour = new Flavour("Chocolat", 1.2, 60);
    Topping topping1 = new Topping("Pépites", 2, 30);
    Topping topping2 = new Topping("Crème", 1.5, 20);
    List<Topping> toppings = new ArrayList<>();

    @Test
    void showBillTest() {
        Stock stock = store.getStock();
        cart.setStore(store);

        stock.addStock(new Dough("Pâte", 0.5, 1000000));
        stock.addStock(new Flavour("Chocolat", 1.2, 1000000));
        stock.addStock(new Topping("Pépites", 2, 1000000));
        stock.addStock(new Topping("Crème", 1.5, 1000000));

        Cookie cookie = new Cookie("CooKie", dough, flavour, toppings);
        Item item = new Item(cookie, 1);
        cart.addToCart(item);
        System.out.println(bill.showBill());

        toppings.add(topping1);
        toppings.add(topping2);

        cookie = new Cookie("CooKie", dough, flavour, toppings);
        item = new Item(cookie, 1);
        cart.addToCart(item);
        System.out.println(bill.showBill());
    }
}
