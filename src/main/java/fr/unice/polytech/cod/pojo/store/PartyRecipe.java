package fr.unice.polytech.cod.pojo.store;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.PartyCookie;
import fr.unice.polytech.cod.pojo.ingredient.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PartyRecipe {
    static HashMap<Ingredient, Boolean> emptyMap = new HashMap<>();
    private static final Cookie birthdayCookie = new Cookie("BirthdayDough", new Dough("BirthdayDough", 3,3), new Flavour("CandyFlavour", 2, 2), new ArrayList<>(List.of(new Topping("Candy", 2, 4),new Topping("Chocolate", 2, 4))), new Mix(Mix.MixState.MIXED), new Cooking(Cooking.CookingState.CHEWY), 120);
    public static PartyCookie birthdayPartyCookieComponent = new PartyCookie(birthdayCookie, PartyCookie.CookieSize.L, "police", PartyCookie.Event.Retirement, emptyMap) {
    };

    private static final Cookie RetirementCookie = new Cookie("RetirementDough", new Dough("RetirementDough", 3,3), new Flavour("ChocolateFlavour", 2, 2), new ArrayList<>(List.of(new Topping("Chocolate", 2, 4),new Topping("Fruits", 2, 4))), new Mix(Mix.MixState.MIXED), new Cooking(Cooking.CookingState.CHEWY), 120);
    public static PartyCookie retirementPartyCookieComponent = new PartyCookie(RetirementCookie, PartyCookie.CookieSize.XL, "princess", PartyCookie.Event.Retirement, emptyMap);

    private static final Cookie WeddingCookie = new Cookie("WeddingDough", new Dough("WeddingDough", 3,3), new Flavour("ChocolateFlavour", 2, 2), new ArrayList<>(List.of(new Topping("Chocolate", 2, 4),new Topping("Fruits", 2, 4))), new Mix(Mix.MixState.MIXED), new Cooking(Cooking.CookingState.CHEWY), 120);
    public static PartyCookie weddingPartyCookieComponent = new PartyCookie(WeddingCookie, PartyCookie.CookieSize.XL, "chat", PartyCookie.Event.Retirement, emptyMap);
}