package fr.unice.polytech.cod;

import fr.unice.polytech.cod.data.CookieBook;
import fr.unice.polytech.cod.data.IngredientCatalog;
import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.*;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.schedule.TimeSlot;
import fr.unice.polytech.cod.store.InvalidStoreException;
import fr.unice.polytech.cod.store.Stock;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.store.*;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.internal.matchers.Or;

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
    StoreLocation storeLocation;
    List<Interval> availableIntervals;
    Order pendingOrder;
    Order inProgressOrder;
    Order retrieveOrder;
    Store store;
    StoreManager storeManager;
    List<Order> historic;

    private final IngredientCatalog ingredientCatalog = IngredientCatalog.instance;

    @Given("a user")
    public void a_user() {
        this.storeLocation =new StoreLocation();
        user = new User(new Cart(), storeLocation);
    }

    @Given("a store named {string}")
    public void the_antibes_store(String name) throws InvalidStoreException {
        user.selectStore(name);
        this.store=user.getStore();
        for (int i =0; i <100; i++)
            store.fillStock(ingredientCatalog.getIngredientList());
    }
    @Given("a valid cookie")
    public void a_valid_cookie() {
        testCookie = new CookieBook().getCookie("Cookie au chocolat");
    }
    @Given("a fidelity account")
    public void a_fidelity_account() {
        user.subscribeToFidelityAccount("name", "email", "password");
    }

    @Given("a non-empty cart with {int} cookie")
    public void a_non_empty_cart_with_cookie(int nbCookies) {
        if(nbCookies <= 0) return;
        cart = user.getCart();
        cart.getItemList().add(new Item(testCookie, nbCookies));
        TimeSlot timeSlot=new TimeSlot(new TimeClock(8,0),new TimeClock(8,15));
        List<TimeSlot> timeSlots=new ArrayList<>();
        timeSlots.add(timeSlot);
        cart.setInterval(new Interval(timeSlots));
    }

    @When("he remove a cookie from his cart")
    public void he_remove_a_cookie_from_his_cart() throws Exception {
        Item item = user.getItemFromCart("Cookie au chocolat");
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
    public void we_choose_a_valid_store() throws InvalidStoreException {
        user.selectStore("Antibes");
    }
    @When("we choose an invalid store")
    public void we_choose_an_invalid_store() {
        try {
            user.selectStore("invalidStore");
        } catch (InvalidStoreException e) {
            this.exception=e;
        }
    }
    @When("he subscribe to the fidelity program as {string} with {string} mail and this password {string}")
    public void he_subscribe_to_the_fidelity_program(String name, String email, String password) {
        user.subscribeToFidelityAccount(name, email, password);
    }
    @When("he order {int} cookies")
    public void he_order_cookies(int numberOfCookies) {
        user.chooseCookies(testCookie, numberOfCookies);
    }

    @Then("the bill is created")
    public void the_bill_is_created() {
        assertNotNull(bill);
    }
    @Then("he take advantage of our loyalty program")
    public void he_take_advantage_of_our_loyalty_program() {
        assertTrue(user.getSubscription().isPresent());
    }

    @Then("an InvalidStoreException is triggered")
    public void an_invalid_store_exception_is_triggered() {
        assertNotNull(this.exception);
        assertTrue(this.exception instanceof InvalidStoreException);
    }
    @Then("the right store is selected in the cart")
    public void the_right_store_is_selected_in_the_cart() {
        assertEquals("Antibes", user.getCart().getStore().getName());
    }
    @Then("his order is created")
    public void his_order_is_created(){
        assertEquals(1, user.getOrders().size());
        assertEquals(1, cart.getStore().getOrderList().size());
        Stock stock = cart.getStore().getStock();
        Optional<Ingredient> ingredient = stock.findIngredientInStock(new Dough("Pate verte", 25, 30));
        assertEquals(0, ingredient.get().getQuantity());
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
       assertEquals(1, cart.getItemQuantity("Cookie au chocolat"));
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
        assertEquals(1,store.getOrderList().size());
        for(TimeSlot timeSlot:interval.getTimeSlots())
        {assertTrue(timeSlot.getOrder().isPresent());
            assertTrue(timeSlot.isReserved());}
    }

    @Then("the Time slots composing the interval are set to reserved")
    public void theTimeSlotsComposingTheIntervalAreSetToReserved() {
        for(TimeSlot timeSlot:interval.getTimeSlots())
        {
            assertTrue(timeSlot.isReserved());}
    }

    @Given("an employee with disponibility only from {int} to {int}")
    public void anEmployeeWithDisponibilityOnlyFromTo(int startingHour, int finishingHour) {
        Chef chef=new Chef(store);
        store.addChef(chef);
        List<TimeSlot> timeSlots=chef.getSchedule().getDaySlot(0).getTimeSlots();
        for (TimeSlot timeSlot:timeSlots){
            if(!(timeSlot.getStartTime().compareTo(new TimeClock(startingHour,0))>=0&&timeSlot.getEndTime().compareTo(new TimeClock(finishingHour,0))<=0))
                timeSlot.setReserved(true);
        }
    }
    @When("he add cookie to his cart")
    public void he_add_cookie_to_his_cart() {
        user.chooseCookies(testCookie, 1);
    }
    @When("a user ask for {int} minute intervals possible")
    public void aUserAskForMinuteIntervalsPossible() {
        availableIntervals=user.getAvailableIntervals(cart.getDuration(),0);
    }

    @Then("he gets only intervals starting and finishing in the {int} to {int} time period with a {int} minute duration")
    public void heGetsOnlyIntervalsStartingAndFinishingInTheToTimePeriodWithAMinuteDuration(int startingHour, int finishingHour,int duration) {
        for (Interval interval:availableIntervals){
            assertTrue(interval.getStartTime().compareTo(new TimeClock(startingHour,0))>=0&&interval.getEndTime().compareTo(new TimeClock(finishingHour,0))<=0);
            assertEquals(duration, interval.getStartTime().timeDifference(interval.getEndTime()));
        }
    }

    @And("the store has no employ")
    public void theStoreHasNoEmploy() {
        store.getListChef().clear();
    }

    @And("he gets no intervals starting before {int} and finishing after")
    public void heGetsNoIntervalsStartingBeforeAndFinishingAfter(int firstEndTime) {
        for (Interval interval:availableIntervals){
            boolean startBeforeEndOfFirstshift=interval.getStartTime().compareTo(new TimeClock(firstEndTime,0))<0;
            boolean endAfterFirstshift=interval.getEndTime().compareTo(new TimeClock(firstEndTime,0))>0;
            //xor
            assertFalse(startBeforeEndOfFirstshift && endAfterFirstshift);
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

    @Given("an order at the state \"([^\"]*)\"$")
    public void an_order_at_the_state(OrderState state) {
        pendingOrder = new Order(cart, user);
        inProgressOrder = new Order(cart, user);
        user.getOrders().add(pendingOrder);
        user.getOrders().add(inProgressOrder);
        if(state.equals(OrderState.PENDING)) pendingOrder.updateState(state);
        else inProgressOrder.updateState(state);
    }

    @When("the user try to cancel his order at the state \"([^\"]*)\"$")
    public void the_user_try_to_cancel_his_order_at_the_state_OrderState(OrderState state) {
        if(state.equals(OrderState.PENDING)) user.cancelOrder(pendingOrder);
        else user.cancelOrder(inProgressOrder);

    }

    @Then("the order is canceled")
    public void the_order_is_canceled() {
        assertFalse(user.getCart().getStore().getOrderList().contains(pendingOrder));
    }

    @Then("the user is notified")
    public void the_user_is_notified() {
        if(!user.getCart().getStore().getOrderList().contains(pendingOrder))
            assertTrue(user.cancelOrder(pendingOrder));
        if(user.getCart().getStore().getOrderList().contains(inProgressOrder))
            assertFalse(user.cancelOrder(inProgressOrder));
    }

    @Then("the order cannot be canceled")
    public void the_order_cannot_be_canceled() {
        assertFalse(user.getCart().getStore().getOrderList().contains(inProgressOrder));
    }

    @Given("a manager")
    public void aManager() {
        storeManager=new StoreManager(store);
        store.setStoreManager(storeManager);
    }

    @When("the manager changes the opening time of the store from {int} to {int}")
    public void theManagerChangesTheOpeningTimeOfTheStoreFromTo(int startingHour, int endHour) {
        this.storeManager.changeOpeningHour(new TimeClock(startingHour,0),new TimeClock(endHour,0));
    }

    @Then("the schedule of the employees start from {int} to {int}")
    public void theScheduleOfTheEmployeesStartFromTo(int startingHour, int endHour) {
        for(Chef chef:store.getListChef()){
            List<TimeSlot> timeSlots=chef.getSchedule().getDaySlot(0).getTimeSlots();
            assertEquals(0,timeSlots.get(0).getStartTime().compareTo(new TimeClock(startingHour,0)));
            assertEquals(0,timeSlots.get(timeSlots.size()-1).getStartTime().compareTo(new TimeClock(endHour,0)));

        }
    }


    //A client can check his last orders

    @Given("past orders")
    public void past_orders() {
        retrieveOrder = new Order(user.getCart(),OrderState.RETRIEVE,user);
    }
    @When("a client ask for his history")
    public void a_client_ask_for_his_history() throws Exception {
        user.subscribeToFidelityAccount("name","email","pw");
        user.getSubscription().get().addOrder(retrieveOrder);

        historic = user.getHistory();
    }
    @Then("he gets all his past orders")
    public void he_gets_all_his_past_orders() {
        assertEquals(1, historic.size());
    }

}
