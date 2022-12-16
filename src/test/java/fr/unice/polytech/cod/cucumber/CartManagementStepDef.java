package fr.unice.polytech.cod.cucumber;

import fr.unice.polytech.cod.components.UserComponent;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.pojo.*;
import fr.unice.polytech.cod.food.ingredient.Dough;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.food.*;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.schedule.TimeSlot;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.*;
import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CartManagementStepDef {

    @Autowired
    UserComponent userComponent;

    User user;
    Cart cart;
    Cookie testCookie;
    List<Cookie> cookieList;
    Exception exception;
    Interval interval;
    Bill bill;
    List<Interval> availableIntervals;
    Order pendingOrder;
    Order inProgressOrder;
    Order retrieveOrder;
    Store store;
    List<Order> historic;

    private final IngredientCatalog ingredientCatalog = IngredientCatalog.instance;
    private final StoreLocation storeLocation = StoreLocation.instance;

    @Autowired
    CartActions cartActions;
    @Autowired
    UserAction userAction;
    @Autowired
    StockModifier stockModifier;
    @Autowired
    StoreModifier storeModifier;
    @Autowired
    CookieBookManager cookieBookManager;
    @Autowired
    FidelityAccountManager fidelityAccountManager;
    @Autowired
    UserRequest userRequest;
    @Autowired
    StockExplorer stockExplorer;
    @Autowired
    IntervalManager intervalManager;
    @Autowired
    CatalogExplorer catalogExplorer;
    @Autowired
    ScheduleActions scheduleActions;

    @Autowired
    StoreFinder storeFinder;

    @Autowired
    OrderStatesAction orderStatesAction;

    @Given("a user")
    public void a_user() {
        this.user = new User();
    }

    @Given("a store named {string}")
    public void the_antibes_store(String name) throws InvalidStoreException {
        userAction.selectStore(name,user.getCart());
        this.store=user.getCart().getStore();
        for(Ingredient ingredient : ingredientCatalog.getIngredientList())
            storeModifier.setTax(store,ingredient.getName(), 3.0);
        //for (int i =0; i <100; i++)
        //    store.fillStock(ingredientCatalog.getIngredientList(), taxesValues);
        stockModifier.addIngredients(store.getStock(),ingredientCatalog.getIngredientList());
    }
    @Given("a valid cookie")
    public void a_valid_cookie() {
        testCookie = cookieBookManager.getCookie(new CookieBook(),"Cookie au chocolat");
    }
    @Given("a fidelity account")
    public void a_fidelity_account() {
        userAction.subscribeToFidelityAccount(user,"name", "email", "password");
    }

    @Given("a non-empty cart with {int} cookie")
    public void a_non_empty_cart_with_cookie(int nbCookies) {
        if(nbCookies <= 0) return;
        cart = user.getCart();
        cart.getItemSet().add(new Item(testCookie, nbCookies));
        TimeSlot timeSlot=new TimeSlot(new TimeClock(8,0),new TimeClock(8,15));
        List<TimeSlot> timeSlots=new ArrayList<>();
        timeSlots.add(timeSlot);
        cart.setInterval(new Interval(timeSlots));
    }

    @Given("an empty cart")
    public void anEmptyCart() {
        cart = user.getCart();
        TimeSlot timeSlot=new TimeSlot(new TimeClock(8,0),new TimeClock(8,15));
        List<TimeSlot> timeSlots=new ArrayList<>();
        timeSlots.add(timeSlot);
        cart.setInterval(new Interval(timeSlots));
    }

    @When("he remove a cookie from his cart")
    public void he_remove_a_cookie_from_his_cart() throws Exception {
        Item item = userRequest.getItemFromCart(user.getCart(),"Cookie au chocolat");
        userAction.removeOneItemFromCart(item,user.getCart());
    }

    @When("he requests the cookie list")
    public void he_requests_the_cookie_list() {
        // Write code here that turns the phrase above into concrete actions
        cookieList = userRequest.viewCatalog(user.getCart().getStore());
    }
    @When("he validate his cart")
    public void he_validate_his_cart() throws Exception {
        cartActions.showCart(user.getCart());
        bill = userAction.validateCart(user);
    }
    @When("we choose a valid store")
    public void we_choose_a_valid_store() throws InvalidStoreException {
        userAction.selectStore("Antibes",cart);
    }
    @When("we choose an invalid store")
    public void we_choose_an_invalid_store() {
        try {
            userAction.selectStore("invalidStore",cart);
        } catch (InvalidStoreException e) {
            this.exception=e;
        }
    }
    @When("he subscribe to the fidelity program as {string} with {string} mail and this password {string}")
    public void he_subscribe_to_the_fidelity_program(String name, String email, String password) {
        userAction.subscribeToFidelityAccount(user,name, email, password);
    }
    @When("he order {int} cookies")
    public void he_order_cookies(int numberOfCookies) {
        userAction.addCookies(testCookie, numberOfCookies,user.getCart());
    }

    @Then("the bill is created")
    public void the_bill_is_created() {
        assertNotNull(bill);
    }
    @Then("he take advantage of our loyalty program")
    public void he_take_advantage_of_our_loyalty_program() {
        assertNotNull(user.getFidelityAccount());
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
        assertEquals(1, user.getUserOrders().size());
        assertEquals(1, cart.getStore().getOrderList().size());
        Stock stock = cart.getStore().getStock();
        Optional<Ingredient> ingredient = stockExplorer.findIngredient(stock,new Dough("Pate verte", 25, 30));
        assertEquals(0, ingredient.get().getQuantity());
    }
    @Then("he receive the entire list")
    public void he_receive_the_entire_list() {
        assertFalse(cookieList.isEmpty());
    }
    @Then("a cookie is added to his cart")
    public void a_cookie_is_added_to_his_cart() {
        assertEquals(2, user.getCart().getItemSet().size());
    }
    @Then("his cart has one item less")
    public void his_cart_has_one_item_less() {
        System.out.println(cart.getItemSet().size());
       assertEquals(1, cartActions.getItemQuantity(cart,"Cookie au chocolat"));
    }
    @Then("he receive a discount for his next order")
    public void he_receive_a_discount_for_his_next_order() {
        assertNotNull(user.getFidelityAccount());
    }
    @Then("he do not receive a discount for his next order")
    public void he_do_not_receive_a_discount_for_his_next_order() {
        assertNull(user.getFidelityAccount());
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
        userAction.chooseInterval(interval,user.getCart());
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
        storeModifier.addChef(store,chef);
        List<TimeSlot> timeSlots=chef.getSchedule().getDaySlots().get(0).getTimeSlots();
        for (TimeSlot timeSlot:timeSlots){
            if(!(timeSlot.getStartTime().compareTo(new TimeClock(startingHour,0))>=0&&timeSlot.getEndTime().compareTo(new TimeClock(finishingHour,0))<=0))
                timeSlot.setReserved(true);
        }
    }
    @When("he add cookie to his cart")
    public void he_add_cookie_to_his_cart() {
        userAction.addCookies(testCookie, 1, cart);
    }
    @When("a user ask for {int} minute intervals possible")
    public void aUserAskForMinuteIntervalsPossible() {
        availableIntervals=userRequest.getAvailableIntervals(store, cart, cartActions.getDuration(cart));
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
        Dough dough = new Dough("DoughName", 1, 2);
        Cookie cookie = new Cookie("CookieName", dough, null, new ArrayList<>(), null, null, 1);
        Item item = new Item(cookie, 1);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(dough);
        Map<Ingredient, Double> taxes = new HashMap<>();
        taxes.put(dough, 5.0);
        stockModifier.addIngredients(store.getStock(), ingredients);
        cart.getItemSet().clear();
        cart.getItemSet().add(item);
        pendingOrder = new Order(cart, user);
        inProgressOrder = new Order(cart, user);
        user.getUserOrders().add(pendingOrder);
        user.getUserOrders().add(inProgressOrder);
        if(state.equals(OrderState.PENDING))orderStatesAction.updateState(pendingOrder, state);
        else orderStatesAction.updateState(inProgressOrder, state);
    }

    @When("the user try to cancel his order at the state \"([^\"]*)\"$")
    public void the_user_try_to_cancel_his_order_at_the_state_OrderState(OrderState state) {
        if(state.equals(OrderState.PENDING)) userComponent.cancelOrder(user,pendingOrder);
        else userComponent.cancelOrder(user, inProgressOrder);
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
        storeManager = new StoreManager(store);
        store.setStoreManager(storeManager);
    }

    @When("the manager changes the opening time of the store from {int} to {int}")
    public void theManagerChangesTheOpeningTimeOfTheStoreFromTo(int startingHour, int endHour) {
        storeModifier.changeOpeningHour(store, new TimeClock(startingHour,0),new TimeClock(endHour,0));
    }

    @Then("the schedule of the employees start from {int} to {int}")
    public void theScheduleOfTheEmployeesStartFromTo(int startingHour, int endHour) {
        for(Chef chef:store.getListChef()){
            List<TimeSlot> timeSlots = scheduleActions.getDaySlot(chef.getSchedule(), 0).getTimeSlots();
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
        userAction.subscribeToFidelityAccount(this.user, "name","email","pw");
        user.getSubscription().get().addOrder(retrieveOrder);

        historic = user.getHistory();
    }
    @Then("he gets all his past orders")
    public void he_gets_all_his_past_orders() {
        assertEquals(1, historic.size());
    }

    @Given("the stock contain ingredients for {string}")
    public void theStockContainIngredientsFor(String cookieName) {
        Cookie cookie = store.getCookieBook().getCookie(cookieName);
        store.fillStock(cookie.getIngredientsList(), store.getTaxes());
    }

    @When("he order {string} a party cookie {string} customized with additional M&Ms")
    public void heOrderAPartyCookieCustomizedWithAdditionalMMs(String size, String cookieName) throws Exception {
        HashMap<Ingredient, Boolean> additional= new HashMap<>();
        additional.put(new Ingredient("M&M's", 1.0, 1), true);
        PartyCookie partyCookie = new PartyCookie(testCookie, getSize(size), "princesse", PartyCookie.Event.Anniversary,additional);
        userAction.addCookies(partyCookie, 1, cart);
    }
    public PartyCookie.CookieSize getSize(String size) throws Exception {
        return switch (size) {
            case "L" -> PartyCookie.CookieSize.L;
            case "XL" -> PartyCookie.CookieSize.XL;
            case "XXL" -> PartyCookie.CookieSize.XXL;
            default -> throw new Exception("ERROR incorrect size of cookie");
        };
    }
}
