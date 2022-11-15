package fr.unice.polytech.cod.decorator_pattern;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.*;

import java.util.ArrayList;
import java.util.List;

public class PartyRecipe {
    private static final Cookie birthdayCookie = new Cookie("BirthdayDough", new Dough("BirthdayDough", 3,3), new Flavour("CandyFlavour", 2, 2), new ArrayList<>(List.of(new Topping("Candy", 2, 4),new Topping("Chocolate", 2, 4))), new Mix(Mix.MixState.MIXED), new Cooking(Cooking.CookingState.CHEWY), 120);
    public static PartyCookieComponent birthdayPartyCookieComponent = new PartyCookie(birthdayCookie, PartyCookieComponent.CookieSize.L) {
    };

    private static final Cookie RetirementCookie = new Cookie("RetirementDough", new Dough("RetirementDough", 3,3), new Flavour("ChocolateFlavour", 2, 2), new ArrayList<>(List.of(new Topping("Chocolate", 2, 4),new Topping("Fruits", 2, 4))), new Mix(Mix.MixState.MIXED), new Cooking(Cooking.CookingState.CHEWY), 120);
    public static PartyCookieComponent retirementPartyCookieComponent = new PartyCookie(RetirementCookie, PartyCookieComponent.CookieSize.XL);

    private static final Cookie WeddingCookie = new Cookie("WeddingDough", new Dough("WeddingDough", 3,3), new Flavour("ChocolateFlavour", 2, 2), new ArrayList<>(List.of(new Topping("Chocolate", 2, 4),new Topping("Fruits", 2, 4))), new Mix(Mix.MixState.MIXED), new Cooking(Cooking.CookingState.CHEWY), 120);
    public static PartyCookieComponent weddingPartyCookieComponent = new PartyCookie(WeddingCookie, PartyCookieComponent.CookieSize.XL);
}