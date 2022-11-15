package fr.unice.polytech.cod;

import fr.unice.polytech.cod.data.CookieBook;
import fr.unice.polytech.cod.data.IngredientCatalog;
import fr.unice.polytech.cod.food.ingredient.Cooking;
import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Flavour;
import fr.unice.polytech.cod.food.ingredient.Mix;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.food.*;
import fr.unice.polytech.cod.store.InvalidStoreException;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;
import fr.unice.polytech.cod.user.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

/**

 Store store = new Store("Antibes");
 CookieBook cookieBook = new CookieBook();
 FidelityAccount fidelityAccount = new FidelityAccount();

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

 */
        System.out.println("FIN");
        return;
    }

    void runTime() {
        //Un store, un user(fidelityAccount), des cuistos
        //Mettre plein d'ingrédient dans le stock
        //Le user choisi des cookies à mettre dans son cart, choisir un magasin et l'heure
        //Passer le temps jusqu'a que la commande soit prête
        //Le client va récupé la commande
    }
}
