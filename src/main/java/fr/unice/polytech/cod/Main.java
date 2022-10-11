package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Store store = new Store();
        CookieBook cookieBook = new CookieBook();
        Cart cart = new Cart(store);
        FidelityAccount fidelityAccount = new FidelityAccount();
        User user = new User(cookieBook, cart, fidelityAccount);

        System.out.println("\nCatalogue : \n" + user.seeCatalogue() + "\n");
        System.out.println("\nUser choose a cookie\n");
        user.chooseCookies(new Cookie("cookieName", new Dough(), new Flavour(), new ArrayList<>()), 12);
        System.out.println("\nCart content : \n");
        user.recapCart();
        System.out.println("\nCart is validate : \n");
        user.validateCart();
    }
}
