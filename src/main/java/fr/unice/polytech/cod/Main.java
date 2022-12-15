package fr.unice.polytech.cod;

import fr.unice.polytech.cod.data.IngredientCatalog;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.food.ingredient.Cooking;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.ingredient.Mix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
