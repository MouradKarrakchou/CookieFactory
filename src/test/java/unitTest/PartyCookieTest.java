package unitTest;

import fr.unice.polytech.cod.decorator_pattern.BirthdayCookie;
import fr.unice.polytech.cod.decorator_pattern.CookieComponent;
import fr.unice.polytech.cod.decorator_pattern.decorator.*;
import fr.unice.polytech.cod.ingredient.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.copyOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartyCookieTest {

    @Test
    public void toppingDecoratorPatternTest(){
        CookieComponent myCookie = new BirthdayCookie();
        Topping chocoTopping = new Topping("chocolate", 2,5);
        CookieComponent myCustomBirthdayCookie = new ToppingDecorator(myCookie, Decorator.DecoratorState.ADD,chocoTopping);

        ArrayList<Ingredient> testList = new ArrayList<>(copyOf(myCookie.getIngredients()));
        testList.add(chocoTopping);

        Assertions.assertEquals(myCookie.getPrice()+ chocoTopping.getPrice(), myCustomBirthdayCookie.getPrice());
        Assertions.assertEquals(myCookie.getName()+" "+chocoTopping.getName(), myCustomBirthdayCookie.getName());
        Assertions.assertEquals(testList, myCustomBirthdayCookie.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirthdayCookie.getCookieSize());
    }
    @Test
    public void cookingDecoratorPatternTest(){
        CookieComponent myCookie = new BirthdayCookie();
        Cooking cooking = new Cooking(Cooking.CookingState.CHEWY);
        CookieComponent myCustomBirthdayCookie = new CookingDecorator(myCookie, cooking);

        Assertions.assertEquals(myCookie.getPrice(), myCustomBirthdayCookie.getPrice());
        Assertions.assertEquals(myCookie.getName(), myCustomBirthdayCookie.getName());
        Assertions.assertEquals(myCookie.getIngredients(), myCustomBirthdayCookie.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirthdayCookie.getCookieSize());

        Cooking cooking2 = new Cooking(Cooking.CookingState.CRUNCHY);
        CookieComponent myCustomBirthdayCookie2 = new CookingDecorator(myCustomBirthdayCookie, cooking2);

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
        CookieComponent myCookie = new BirthdayCookie();
        Mix mix = new Mix(Mix.MixState.MIXED);
        CookieComponent myCustomBirtdayCookie = new MixDecorator(myCookie, mix);

        Assertions.assertEquals(myCookie.getPrice(), myCustomBirtdayCookie.getPrice());
        Assertions.assertEquals(myCookie.getName(), myCustomBirtdayCookie.getName());
        Assertions.assertEquals(myCookie.getIngredients(), myCustomBirtdayCookie.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirtdayCookie.getCookieSize());

        Mix mix2 = new Mix(Mix.MixState.TOPPED);
        CookieComponent myCustomBirthdayCookie2 = new MixDecorator(myCustomBirtdayCookie, mix2);

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
        CookieComponent myCookie = new BirthdayCookie();
        Flavour flavour = new Flavour("chocolate", 12, 5);
        CookieComponent myCustomBirtdayCookie = new FlavourDecorator(myCookie, flavour, Decorator.DecoratorState.ADD);

        ArrayList<Ingredient> testList = new ArrayList<>(copyOf(myCookie.getIngredients()));
        testList.add(flavour);

        Assertions.assertEquals(myCookie.getPrice()+ flavour.getPrice(), myCustomBirtdayCookie.getPrice());
        Assertions.assertEquals(myCookie.getName()+" "+flavour.getName(), myCustomBirtdayCookie.getName());
        Assertions.assertEquals(testList, myCustomBirtdayCookie.getIngredients());
        Assertions.assertEquals(myCookie.getCookieSize(), myCustomBirtdayCookie.getCookieSize());
    }
}
