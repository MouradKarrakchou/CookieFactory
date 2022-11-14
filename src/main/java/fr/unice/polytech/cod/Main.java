package fr.unice.polytech.cod;

import fr.unice.polytech.cod.decorator_pattern.BirthdayCookie;
import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.decorator_pattern.decorator.Decorator;
import fr.unice.polytech.cod.decorator_pattern.decorator.ToppingDecorator;
import fr.unice.polytech.cod.ingredient.*;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.StoreManager;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Initializing the display
        Display.start();

        Store store = new Store("Antibes");
        final CookieBook cookieBook = CookieBook.instance;
        Cart cart = new Cart();
        FidelityAccount fidelityAccount = new FidelityAccount();
        User user = new User(cookieBook, cart,new StoreManager());

        Display.displayCookies(user.viewCatalog());

        Display.info("(User choose a cookie)");
        user.chooseCookies(new Cookie("Chocolala", new Dough("Pate verte",25,50), new Flavour("Vert",25,50), new ArrayList<>(),new Mix(Mix.MixState.MIXED),
                new Cooking(Cooking.CookingState.CHEWY), 10), 12);

        Display.info("(User watch recapCart)\n");
        user.recapCart();

        Stock s = new Stock();
        Dough d200 = new Dough("les", 2, 200);
        Dough d100 = new Dough("les", 2, 100);
        s.addStock(d200);
        s.addStock(d100);
        System.out.println(s.lock(d100));
        System.out.println("end");
    }

    void runTime() {
        //Un store, un user(fidelityAccount), des cuistos
        //Mettre plein d'ingrédient dans le stock
        //Le user choisi des cookies à mettre dans son cart, choisir un magasin et l'heure
        //Passer le temps jusqu'a que la commande soit prête
        //Le client va récupé la commande
    }
}
