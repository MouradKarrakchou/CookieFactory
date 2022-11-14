package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Dough;
import fr.unice.polytech.cod.ingredient.Flavour;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.ingredient.Topping;
import fr.unice.polytech.cod.ingredient.*;
import fr.unice.polytech.cod.store.InvalidStoreExepection;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.StoreManager;
import fr.unice.polytech.cod.store.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CartManagementStepDef {

    User user;
    Cart cart;
    Cookie testCookie;
    List<Cookie> cookieList;
    Exception exception;
    Interval interval;
    Bill bill;
    StoreManager storeManager;
    List<Interval> availableIntervals;

    @Given("a user")
    public void a_user() {
        this.storeManager=new StoreManager();
        user = new User(new CookieBook(),new Cart(),storeManager);
    }

    @Given("a store named {string}")
    public void the_antibes_store(String name) throws InvalidStoreExepection {
        IngredientCatalog ingredientCatalog = new IngredientCatalog();
        user.selectStore(name);
        for (int i =0; i <100; i++)
            user.getStore().getStock().addStockList(ingredientCatalog.getIngredientList());
    }
    @Given("a valid cookie")
    public void a_valid_cookie() {
        IngredientCatalog ingredientCatalog = new IngredientCatalog();
        testCookie = new Cookie("testCookie",
                ingredientCatalog.getDoughList().get(0),
                ingredientCatalog.getFlavourList().get(0),
                ingredientCatalog.getToppingList(),
                new Mix(Mix.MixState.MIXED),
                new Cooking(Cooking.CookingState.CHEWY),
                10);
    }
    @Given("a fidelity account")
    public void a_fidelity_account() throws InvalidStoreExepection {
        user.subscribeToFidelityAccount("name", "email", "password");
    }

    @Given("a non-empty cart with {int} cookie")
    public void a_non_empty_cart_with_cookie(int nbCookies) {
        cart = user.getCart();
        cart.getItemList().add(new Item(testCookie, nbCookies));
        TimeSlot timeSlot=new TimeSlot(new TimeClock(8,0),new TimeClock(8,15));
        List<TimeSlot> timeSlots=new ArrayList<>();
        timeSlots.add(timeSlot);
        cart.setInterval(new Interval(timeSlots));
    }



    @When("he add cookie to his cart")
    public void he_add_cookie_to_his_cart() {
        user.chooseCookies(testCookie, 1);
    }
    @When("he remove a cookie from his cart")
    public void he_remove_a_cookie_from_his_cart() throws Exception {
        List<Item> allItems =  user.getAllItemsFromCart();
        Item item = user.getItemFromCart("testCookie");
        user.removeOneItemFromCart(item);
    }
    @When("he requests the cookie list")
    public void he_requests_the_cookie_list() {
        // Write code here that turns the phrase above into concrete actions
        cookieList = user.viewCatalog();
    }
    @When("he validate his cart")
    public void he_validate_his_cart() throws Exception {
        user.getCart().showCart();
        bill = user.validateCart();
    }
    @When("we choose a valid store")
    public void we_choose_a_valid_store() throws InvalidStoreExepection {
        user.selectStore("Antibes");
    }
    @When("we choose an invalid store")
    public void we_choose_an_invalid_store() {
        try {
            user.selectStore("invalidStore");
        } catch (InvalidStoreExepection e) {
            this.exception=e;
        }
    }
    @When("he subscribe to the fidelity program as {string} with {string} mail and this password {string}")
    public void he_subscribe_to_the_fidelity_program(String name, String email, String password) {
        user.subscribeToFidelityAccount(name, email, password);
    }
    @When("he order {int} cookies")
    public void he_order_cookies(int numberOfCookies) throws InvalidStoreExepection {
        user.chooseCookies(testCookie, numberOfCookies);
    }

    @Then("the bill is created")
    public void the_bill_is_created() throws InvalidStoreExepection {
       assertTrue(bill != null);
    }
    @Then("he take advantage of our loyalty program")
    public void he_take_advantage_of_our_loyalty_program() {
        assertTrue(user.getSubscription().isPresent());
    }

    @Then("an InvalidStoreException is triggered")
    public void an_invalid_store_exception_is_triggered() {
        assertTrue(this.exception!=null);
        if (this.exception!=null)
            assertTrue(this.exception instanceof InvalidStoreExepection);
    }
    @Then("the right store is selected in the cart")
    public void the_right_store_is_selected_in_the_cart() {
        Assertions.assertTrue(user.getCart().getStore().getName().equals("Antibes"));
    }
    @Then("his order is created")
    public void his_order_is_created(){
        Assertions.assertEquals(1, user.getOrders().size());
        Assertions.assertEquals(1, cart.getStore().getOrderList().size());
        Stock stock = cart.getStore().getStock();
        Optional<Ingredient> ingredient = stock.findIngredientInStock(new Dough("Pate verte", 25, 30));
        Assertions.assertEquals(0, ingredient.get().getQuantity());
    }
    @Then("he receive the entire list")
    public void he_receive_the_entire_list() {
        assertFalse(cookieList.isEmpty());
    }
    @Then("a cookie is added to his cart")
    public void a_cookie_is_added_to_his_cart() {
        assertEquals(2, user.getCart().getItemList().size());
    }
    @Then("his cart has one item less")
    public void his_cart_has_one_item_less() {
       assertEquals(1, cart.getItemQuantity("testCookie"));
    }
    @Then("he receive a discount for his next order")
    public void he_receive_a_discount_for_his_next_order() {
        assertTrue(user.hasDiscount());
    }
    @Then("he do not receive a discount for his next order")
    public void he_do_not_receive_a_discount_for_his_next_order() {
        assertFalse(user.hasDiscount());
    }




    @Given("a valid interval")
    public void aValidInterval() {
        ArrayList<TimeSlot> timeSlots=new ArrayList<>();
        timeSlots.add(new TimeSlot(new TimeClock(8,0),new TimeClock(8,15)));
        timeSlots.add(new TimeSlot(new TimeClock(8,15),new TimeClock(8,30)));
        timeSlots.add(new TimeSlot(new TimeClock(8,30),new TimeClock(8,45)));
        timeSlots.add(new TimeSlot(new TimeClock(8,45),new TimeClock(9,0)));

        interval=new Interval(timeSlots);
    }

    @When("a user chooses an interval")
    public void aUserChoosesAnInterval() {
        this.user.chooseInterval(interval);
    }

    @Then("the order is associated with the Time slots composing the interval are")
    public void theOrderIsAssociatedWithTheTimeSlotsComposingTheIntervalAre() {
        Store store=this.user.getStore();
        assertEquals(1,store.getOrderList().size());
        for(TimeSlot timeSlot:interval.getTimeSlots())
        {assertTrue(timeSlot.getOrder().isPresent());
            assertTrue(timeSlot.reserved);}
    }

    @Then("the Time slots composing the interval are set to reserved")
    public void theTimeSlotsComposingTheIntervalAreSetToReserved() {
        for(TimeSlot timeSlot:interval.getTimeSlots())
        {
            assertTrue(timeSlot.reserved);}
    }

    @Given("an employee with disponibility only from {int} to {int}")
    public void anEmployeeWithDisponibilityOnlyFromTo(int startingHour, int finishingHour) {
        Chef chef=new Chef(user.getStore());
        user.getStore().addChef(chef);
        List<TimeSlot> timeSlots=chef.getSchedule().getDaySlot().getTimeSlots();
        for (TimeSlot timeSlot:timeSlots){
            if(!(timeSlot.getStartTime().compareTo(new TimeClock(startingHour,0))>=0&&timeSlot.getEndTime().compareTo(new TimeClock(finishingHour,0))<=0))
                timeSlot.setReserved(true);
        }
    }


    @When("a user ask for {int} minute intervals possible")
    public void aUserAskForMinuteIntervalsPossible(int duration ) {
        availableIntervals=user.getAvailableIntervals(duration);
    }

    @Then("he gets only intervals starting and finishing in the {int} to {int} time period with a {int} minute duration")
    public void heGetsOnlyIntervalsStartingAndFinishingInTheToTimePeriodWithAMinuteDuration(int startingHour, int finishingHour,int duration) {
        for (Interval interval:availableIntervals){
            assertTrue(interval.getStartTime().compareTo(new TimeClock(startingHour,0))>=0&&interval.getEndTime().compareTo(new TimeClock(finishingHour,0))<=0);
            assertTrue(interval.getStartTime().timeDifference(interval.getEndTime())==duration);
        }
    }

    @And("the store has no employ")
    public void theStoreHasNoEmploy() {
        user.getStore().getListChef().clear();
    }

    @And("he gets no intervals starting before {int} and finishing after")
    public void heGetsNoIntervalsStartingBeforeAndFinishingAfter(int firstEndTime) {
        for (Interval interval:availableIntervals){
            boolean startBeforeEndOfFirstshift=interval.getStartTime().compareTo(new TimeClock(firstEndTime,0))<0;
            boolean endAfterFirstshift=interval.getEndTime().compareTo(new TimeClock(firstEndTime,0))>0;
            //xor
            assertTrue(!(startBeforeEndOfFirstshift && endAfterFirstshift));
        }
    }

    @And("he gets the right number of disponibility for a {int} hours disponibility with a {int} min interval")
    public void heGetsTheRightNumberOfDisponibilityForAHoursDisponibilityWithAMinInterval(int numberOfHour, int intervalDurantion) {
        int numberOfMinute=numberOfHour*60;
        if (intervalDurantion%15!=0)
            numberOfMinute-=(intervalDurantion/15+1)*15;
        else
            numberOfMinute-=intervalDurantion;
        int numberOfInterval=numberOfMinute/15+1;
        assertEquals(numberOfInterval,availableIntervals.size());
    }
}
