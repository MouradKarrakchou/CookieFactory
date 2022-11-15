package unitTest;

import fr.unice.polytech.cod.decorator_pattern.PartyCookie;
import fr.unice.polytech.cod.decorator_pattern.PartyRecipe;
import fr.unice.polytech.cod.decorator_pattern.decorator.*;
import fr.unice.polytech.cod.food.ingredient.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static java.util.List.copyOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartyCookieTest {

    @Test
    public void toppingDecoratorPatternTest(){
        PartyCookie myCookie = PartyRecipe.birthdayPartyCookie;
        Topping chocoTopping = new Topping("chocolate", 2,5);
        PartyCookie myCustomBirthdayCookie = new ToppingDecorator(myCookie, Decorator.DecoratorState.ADD,chocoTopping);

        ArrayList<Ingredient> testList = new ArrayList<>(copyOf(myCookie.getIngredients()));
        testList.add(chocoTopping);

        Assertions.assertEquals(myCookie.getPrice()+ chocoTopping.getPrice(), myCustomBirthdayCookie.getPrice());
        Assertions.assertEquals(myCookie.getName()+" "+chocoTopping.getName(), myCustomBirthdayCookie.getName());
        Assertions.assertEquals(testList, myCustomBirthdayCookie.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirthdayCookie.getCookieSize());
    }
    @Test
    public void cookingDecoratorPatternTest(){
        PartyCookie myCookie = PartyRecipe.birthdayPartyCookie;
        Cooking cooking = new Cooking(Cooking.CookingState.CHEWY);
        PartyCookie myCustomBirthdayCookie = new CookingDecorator(myCookie, cooking);

        Assertions.assertEquals(myCookie.getPrice(), myCustomBirthdayCookie.getPrice());
        Assertions.assertEquals(myCookie.getName(), myCustomBirthdayCookie.getName());
        Assertions.assertEquals(myCookie.getIngredients(), myCustomBirthdayCookie.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirthdayCookie.getCookieSize());

        Cooking cooking2 = new Cooking(Cooking.CookingState.CRUNCHY);
        PartyCookie myCustomBirthdayCookie2 = new CookingDecorator(myCustomBirthdayCookie, cooking2);

        ArrayList<Ingredient> testList = new ArrayList<>(copyOf(myCookie.getIngredients()));
        for(int i = 0 ; i < testList.size() ; i++){
            if(testList.get(i) instanceof Cooking){
                testList.set(i, cooking2);
            }
        }
        Assertions.assertEquals(myCookie.getPrice()-cooking.getPrice()+cooking2.getPrice(), myCustomBirthdayCookie2.getPrice());
        Assertions.assertEquals(myCookie.getName(), myCustomBirthdayCookie2.getName());
        Assertions.assertEquals(testList, myCustomBirthdayCookie2.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirthdayCookie2.getCookieSize());
    }

    @Test
    public void mixDecoratorPatternTest(){
        PartyCookie myCookie = PartyRecipe.birthdayPartyCookie;
        Mix mix = new Mix(Mix.MixState.MIXED);
        PartyCookie myCustomBirtdayCookie = new MixDecorator(myCookie, mix);

        Assertions.assertEquals(myCookie.getPrice(), myCustomBirtdayCookie.getPrice());
        Assertions.assertEquals(myCookie.getName(), myCustomBirtdayCookie.getName());
        Assertions.assertEquals(myCookie.getIngredients(), myCustomBirtdayCookie.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirtdayCookie.getCookieSize());

        Mix mix2 = new Mix(Mix.MixState.TOPPED);
        PartyCookie myCustomBirthdayCookie2 = new MixDecorator(myCustomBirtdayCookie, mix2);

        ArrayList<Ingredient> testList = new ArrayList<>(copyOf(myCookie.getIngredients()));
        for(int i = 0 ; i < testList.size() ; i++){
            if(testList.get(i) instanceof Mix){
                testList.set(i, mix2);
            }
        }
        Assertions.assertEquals(myCookie.getPrice()-mix.getPrice()+mix2.getPrice(), myCustomBirthdayCookie2.getPrice());
        Assertions.assertEquals(myCookie.getName(), myCustomBirthdayCookie2.getName());
        Assertions.assertEquals(testList, myCustomBirthdayCookie2.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirthdayCookie2.getCookieSize());
    }

    @Test
    public void flavourDecoratorPatternTest(){
        PartyCookie myCookie = PartyRecipe.birthdayPartyCookie;
        Flavour flavour = new Flavour("chocolate", 12, 5);
        PartyCookie myCustomBirtdayCookie = new FlavourDecorator(myCookie, flavour, Decorator.DecoratorState.ADD);

        ArrayList<Ingredient> testList = new ArrayList<>(copyOf(myCookie.getIngredients()));
        testList.add(flavour);

        Assertions.assertEquals(myCookie.getPrice()+ flavour.getPrice(), myCustomBirtdayCookie.getPrice());
        Assertions.assertEquals(myCookie.getName()+" "+flavour.getName(), myCustomBirtdayCookie.getName());
        Assertions.assertEquals(testList, myCustomBirtdayCookie.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirtdayCookie.getCookieSize());
    }
}
