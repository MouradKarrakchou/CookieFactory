package fr.unice.polytech.cod;

import fr.unice.polytech.cod.pojo.IngredientCatalog;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Cooking;
import fr.unice.polytech.cod.food.ingredient.Mix;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final IngredientCatalog ingredientCatalog = IngredientCatalog.instance;

        Cookie cookie = new Cookie("Cookie à la pistache",
                ingredientCatalog.getDough("peanut butter"),
                ingredientCatalog.getFlavour("chili"),
                List.of(ingredientCatalog.getTopping("milk chocolate")),
                new Mix(Mix.MixState.MIXED),
                new Cooking(Cooking.CookingState.CHEWY),
                10);

        Cookie cookie1 = cookie.clone();

        cookie.getDough().increaseQuantity(33);
        cookie1.getDough().increaseQuantity(5);

        System.out.println(cookie.getDough().getQuantity() + " / " + cookie1.getDough().getQuantity() );

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
