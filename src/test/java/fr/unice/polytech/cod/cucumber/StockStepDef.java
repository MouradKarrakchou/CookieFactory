package fr.unice.polytech.cod.cucumber;

import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.interfaces.StockExplorer;
import fr.unice.polytech.cod.interfaces.StockModifier;
import fr.unice.polytech.cod.pojo.Stock;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class StockStepDef {
    private Stock stock;

    @Autowired
    StockModifier stockModifier;
    @Autowired
    StockExplorer stockExplorer;


    @Given("an empty stock")
    public void a_user() {
        stock = new Stock();
    }

    @When("{int} grams of {string} is added to the stock")
    public void kgOfFlourIsAddedToTheStock(int quantity, String ingredientName) {
        stockModifier.addIngredient(this.stock, new Ingredient(ingredientName, 0, quantity));
    }

    @Then("the stock has {int} grams of {string}")
    public void theStockHasKgOfFlour(int quantity, String ingredientName) {
        Optional<Ingredient> _ingredient = stockExplorer.findIngredient(this.stock, new Ingredient(ingredientName, 0, 0));
        assertTrue(_ingredient.isPresent());
        Ingredient ingredient = _ingredient.get();
        assertEquals(quantity, ingredient.getQuantity(), 0.0);
    }

    @When("{int} grams of {string} is locked")
    public void gramsOfIsLocked(int quantity, String ingredientName) {
        stockModifier.lock(this.stock, new Ingredient(ingredientName, 0, quantity));
    }

    @Then("we can not lock {int} grams of {string}")
    public void weCanNotLockGramsOf(int quantity, String ingredientName) {
        assertFalse(stockModifier.lock(this.stock, new Ingredient(ingredientName, 0, quantity)));
    }

    @And("{int} grams of {string}")
    public void gramsOf(int quantity, String ingredientName) {
        stockModifier.addIngredient(this.stock, new Ingredient(ingredientName, 0, quantity));
    }

    @Then("we can lock {int} grams of {string}")
    public void weCanLockGramsOf(int quantity, String ingredientName) {
        assertTrue(stockModifier.lock(this.stock, new Ingredient(ingredientName, 0, quantity)));
    }
}
