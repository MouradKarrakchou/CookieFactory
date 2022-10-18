package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        // Initializing the display
        Display.start();

        Store store = new Store();
        CookieBook cookieBook = new CookieBook();
        Cart cart = new Cart(store);
        FidelityAccount fidelityAccount = new FidelityAccount();
        User user = new User(cookieBook, cart, fidelityAccount);

        user.viewCatalog();

        Display.info("(User choose a cookie)");
        user.chooseCookies(new Cookie("Chocolala", new Dough("Pate verte",25,50), new Flavour("Vert",25,50), new ArrayList<>()), 12);

        Display.info("(User watch recapCart)\n");
        user.recapCart();

        System.out.println("\nCart is validate : \n");
        //user.validateCart();
    }
}
