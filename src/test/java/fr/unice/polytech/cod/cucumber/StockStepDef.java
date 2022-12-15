package fr.unice.polytech.cod.cucumber;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.store.Stock;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StockStepDef {
    private Stock stock;


    @Given("an empty stock")
    public void a_user() {
        stock = new Stock();
    }

    @When("{int} grams of {string} is added to the stock")
    public void kgOfFlourIsAddedToTheStock(int quantity, String ingredientName) {
        stock.addStock(new Ingredient(ingredientName, 0, quantity));
    }

    @Then("the stock has {int} grams of {string}")
    public void theStockHasKgOfFlour(int quantity, String ingredientName) {
        Optional<Ingredient> _ingredient = stock.findIngredientInStock(new Ingredient(ingredientName, 0, 0));
        assertTrue(_ingredient.isPresent());
        Ingredient ingredient = _ingredient.get();

        assertEquals(quantity, ingredient.getQuantity(), 0.0);
    }

    @When("{int} grams of {string} is locked")
    public void gramsOfIsLocked(int quantity, String ingredientName) {
        stock.lock(new Ingredient(ingredientName, 0, quantity));
    }

    @Then("we can not lock {int} grams of {string}")
    public void weCanNotLockGramsOf(int quantity, String ingredientName) {
        assertFalse(stock.lock(new Ingredient(ingredientName, 0, quantity)));
    }

    @And("{int} grams of {string}")
    public void gramsOf(int quantity, String ingredientName) {
        stock.addStock(new Ingredient(ingredientName, 0, quantity));
    }

    @Then("we can lock {int} grams of {string}")
    public void weCanLockGramsOf(int quantity, String ingredientName) {
        assertTrue(stock.lock(new Ingredient(ingredientName, 0, quantity)));
    }
}
