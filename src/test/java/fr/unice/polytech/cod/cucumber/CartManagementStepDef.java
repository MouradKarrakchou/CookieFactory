package fr.unice.polytech.cod.cucumber;

import fr.unice.polytech.cod.components.CartHandler;
import fr.unice.polytech.cod.components.CookieBookManager;
import fr.unice.polytech.cod.components.UserManager;
import fr.unice.polytech.cod.exceptions.CookieAlreadyExistingException;
import fr.unice.polytech.cod.exceptions.NotMatchingCatalogRequirementException;
import fr.unice.polytech.cod.food.ingredient.*;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.pojo.*;
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
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class CartManagementStepDef {

    @Autowired
    UserManager userManager;

    User user;
    Cart cart;
    Cookie testCookie;
    List<Cookie> cookieList;
    Exception exception;
    Interval interval;
    Bill bill;
    List<Interval> availableIntervals;
    Order order;
    Order inProgressOrder;
    Order retrieveOrder;
    Store store;
    List<Order> historic;
    CookieBook cookieBook;

    private final IngredientCatalog ingredientCatalog = IngredientCatalog.instance;


    @Autowired
    BrandManagerActions brandManagerActions;
    @Autowired
    CartActions cartActions;

    @Autowired
    UserAction userAction;

    @Autowired
    StockModifier stockModifier;

    @Autowired
    StoreModifier storeModifier;

    @Autowired
    ICookieBookManager iCookieBookManager;

    @Autowired
    IFidelityAccountManager iFidelityAccountManager;

    @Autowired
    UserRequest userRequest;

    @Autowired
    StockExplorer stockExplorer;

    @Autowired
    IIntervalManager iIntervalManager;

    @Autowired
    ICatalogExplorer iCatalogExplorer;

    @Autowired
    ScheduleActions scheduleActions;

    @Autowired
    IStoreFinder iStoreFinder;

    @Autowired
    OrderStatesAction orderStatesAction;

    @Autowired
    CartHandler cartHandler;

    @Autowired
    CookieBookManager cookieBookManager;

    boolean historicException = false;
    boolean emptyCartException = false;

    @Given("a user")
    public void a_user() {
        this.user = new User();
        this.cart=user.getCart();
    }

    @Given("a store named {string}")
    public void the_antibes_store(String name) throws InvalidStoreException {
        AllStores.getInstance().getStoreList().clear();
        AllStores.getInstance().getStoreList().addAll(List.of(
                new Store("Antibes"),
                new Store("Nice"),
                new Store("Sophia")
        ));
        userAction.selectStore(name,user.getCart());
        this.store=user.getCart().getStore();
        //for (int i =0; i <100; i++)
        //    store.fillStock(ingredientCatalog.getIngredientList(), taxesValues);
        stockModifier.addIngredients(store.getStock(),ingredientCatalog.getIngredientList());
    }
    @Given("a valid cookie")
    public void a_valid_cookie() {
        testCookie = iCookieBookManager.getCookie(cookieBook,"Cookie au chocolat");
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
        for (Ingredient ingredient : store.getStock().getIngredients()) {
            ingredient.setQuantity(nbCookies);
        }
    }

    @Given("an empty cart")
    public void anEmptyCart() {
        cart = user.getCart();
        cart.getItemSet().clear();
        TimeSlot timeSlot=new TimeSlot(new TimeClock(8,0),new TimeClock(8,15));
        List<TimeSlot> timeSlots=new ArrayList<>();
        timeSlots.add(timeSlot);
        cart.setInterval(new Interval(timeSlots));
    }

    @When("the order change to {string}")
    public void the_order_change_to(String string) {
        user = new User();
        Cart cart = new Cart();
        order = new Order(cart, user);
        order.setOrderState(OrderState.READY);
    }

    @When("the client overdue the timer")
    public void the_client_overdue_the_timer() throws InterruptedException {
        Thread.sleep(1000);
    }

    @When("he remove a cookie from his cart")
    public void he_remove_a_cookie_from_his_cart() throws Exception {
        Item item = userRequest.getItemFromCart(user.getCart(),"Cookie au chocolat");
        userAction.removeCookies(item.getCookie(),1,user.getCart());
    }

    @When("he requests the cookie list")
    public void he_requests_the_cookie_list() {
        cookieList = userRequest.viewCatalog(user.getCart().getStore());
    }
    @When("he validate his cart")
    public void he_validate_his_cart() {
        cartActions.showCart(user.getCart());
        try {
            bill = userAction.validateCart(user);
        } catch (Exception e) {
            emptyCartException = true;
        }
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

    @When("a user ask for {int} minute intervals possible")
    public void aUserAskForMinuteIntervalsPossible(int minutes) {
        cart.getItemSet().add(new Item(new Cookie("Cookie au chocolat",
                iCatalogExplorer.getDough(ingredientCatalog,"chocolate"),
                iCatalogExplorer.getFlavour(ingredientCatalog,"chili"),
                List.of(iCatalogExplorer.getTopping(ingredientCatalog,"milk chocolate"),iCatalogExplorer.getTopping(ingredientCatalog,"M&M’s")),
                new Mix(Mix.MixState.MIXED),
                new Cooking(Cooking.CookingState.CHEWY),
                minutes-15),1));
        availableIntervals = userRequest.getAvailableIntervals(store, cart,0);
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
        order = new Order(cart, user);
        inProgressOrder = new Order(cart, user);
        user.getUserOrders().add(order);
        user.getUserOrders().add(inProgressOrder);
        order.setWaitingTime(500);
        if(state.equals(OrderState.PENDING))orderStatesAction.updateState(order, state);
        else orderStatesAction.updateState(inProgressOrder, state);
    }

    @When("the user try to cancel his order at the state \"([^\"]*)\"$")
    public void the_user_try_to_cancel_his_order_at_the_state_OrderState(OrderState state) {
        if(state.equals(OrderState.PENDING)) userManager.cancelOrder(user, order);
        else userManager.cancelOrder(user, inProgressOrder);
    }

    @Then("the order is canceled")
    public void the_order_is_canceled() {
        assertFalse(user.getCart().getStore().getOrderList().contains(order));
    }

    @Then("the user is notified")
    public void the_user_is_notified() {
        if(!user.getCart().getStore().getOrderList().contains(order))
            assertTrue(userAction.cancelOrder(user.getCart(), user.getUserOrders(), order));
        if(user.getCart().getStore().getOrderList().contains(inProgressOrder))
            assertFalse(userAction.cancelOrder(user.getCart(), user.getUserOrders(), inProgressOrder));
    }

    @Then("the order cannot be canceled")
    public void the_order_cannot_be_canceled() {
        assertFalse(user.getCart().getStore().getOrderList().contains(inProgressOrder));
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
    public void a_client_ask_for_his_history() {
        try {
            historic = userRequest.getHistory(user.getFidelityAccount());
        } catch (Exception e) {
            historicException = true;
        }
    }
    @Then("he gets all his past orders")
    public void he_gets_all_his_past_orders() {
        assertEquals(1, historic.size());
    }

    @Given("the stock contain ingredients for {string}")
    public void theStockContainIngredientsFor(String cookieName) {
        Cookie cookie = iCookieBookManager.getCookie(cookieBook,cookieName);
        stockModifier.addIngredients(store.getStock(), cookie.getIngredientsList());
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

    @And("an initialised cookie book")
    public void anInitialisedCookieBook() throws NotMatchingCatalogRequirementException, CookieAlreadyExistingException {
        cookieBook=new CookieBook();
        iCookieBookManager.addCookieRecipe(cookieBook,
                new Cookie("Cookie à la vanille",
                        iCatalogExplorer.getDough(ingredientCatalog,"plain"),
                        iCatalogExplorer.getFlavour(ingredientCatalog,"vanilla"),
                        List.of(iCatalogExplorer.getTopping(ingredientCatalog,"milk chocolate"),iCatalogExplorer.getTopping(ingredientCatalog,"white chocolate")),
                        new Mix(Mix.MixState.MIXED),
                        new Cooking(Cooking.CookingState.CHEWY),
                        5)
        );
        iCookieBookManager.addCookieRecipe(cookieBook,
                new Cookie("Cookie au chocolat",
                        iCatalogExplorer.getDough(ingredientCatalog,"plain"),
                        iCatalogExplorer.getFlavour(ingredientCatalog,"chocolate"),
                        List.of(iCatalogExplorer.getTopping(ingredientCatalog,"milk chocolate"),iCatalogExplorer.getTopping(ingredientCatalog,"M&M’s")),
                        new Mix(Mix.MixState.MIXED),
                        new Cooking(Cooking.CookingState.CHEWY),
                        10)
        );
        iCookieBookManager.addCookieRecipe(cookieBook,
                new Cookie("Cookie à la pistache",
                        iCatalogExplorer.getDough(ingredientCatalog,"peanut butter"),
                        iCatalogExplorer.getFlavour(ingredientCatalog,"chili"),
                        List.of(iCatalogExplorer.getTopping(ingredientCatalog,"milk chocolate")),
                        new Mix(Mix.MixState.MIXED),
                        new Cooking(Cooking.CookingState.CHEWY),
                        10)
        );}

    @Given("a cookieBook")
    public void a_cookieBook() throws InvalidStoreException {
        cookieBook = userAction.selectStore("Antibes", cart).getCookieBook();
    }
    @When("when a brandManager remove a cookie to the cookie book")
    public void when_a_brand_manager_remove_a_cookie_to_the_cookie_book() throws Exception {
        brandManagerActions.removeCookie(cookieBookManager.getCookie(cookieBook, "Cookie au chocolat"), "Antibes");
    }
    @Then("the cookie is remove to the cookie book")
    public void the_cookie_is_remove_to_the_cookie_book() {
        assertEquals(2, cookieBook.getCookies().size());
    }

    @And("an initialised cookie book of the store")
    public void anInitialisedCookieBookOfTheStore() throws NotMatchingCatalogRequirementException, CookieAlreadyExistingException {
        cookieBook=store.getCookieBook();
        store.getCookieBook().getCookies().clear();
        iCookieBookManager.addCookieRecipe(cookieBook,
                new Cookie("Cookie à la vanille",
                        iCatalogExplorer.getDough(ingredientCatalog,"plain"),
                        iCatalogExplorer.getFlavour(ingredientCatalog,"vanilla"),
                        List.of(iCatalogExplorer.getTopping(ingredientCatalog,"milk chocolate"),iCatalogExplorer.getTopping(ingredientCatalog,"white chocolate")),
                        new Mix(Mix.MixState.MIXED),
                        new Cooking(Cooking.CookingState.CHEWY),
                        5)
        );
        iCookieBookManager.addCookieRecipe(cookieBook,
                new Cookie("Cookie au chocolat",
                        iCatalogExplorer.getDough(ingredientCatalog,"plain"),
                        iCatalogExplorer.getFlavour(ingredientCatalog,"chocolate"),
                        List.of(iCatalogExplorer.getTopping(ingredientCatalog,"milk chocolate"),iCatalogExplorer.getTopping(ingredientCatalog,"M&M’s")),
                        new Mix(Mix.MixState.MIXED),
                        new Cooking(Cooking.CookingState.CHEWY),
                        10)
        );
        iCookieBookManager.addCookieRecipe(cookieBook,
                new Cookie("Cookie à la pistache",
                        iCatalogExplorer.getDough(ingredientCatalog,"peanut butter"),
                        iCatalogExplorer.getFlavour(ingredientCatalog,"chili"),
                        List.of(iCatalogExplorer.getTopping(ingredientCatalog,"milk chocolate")),
                        new Mix(Mix.MixState.MIXED),
                        new Cooking(Cooking.CookingState.CHEWY),
                        10)
        );
    }

    @When("when a brandManager add a cookie to the cookie book")
    public void when_a_brand_manager_add_a_cookie_to_the_cookie_book() throws Exception {
        brandManagerActions.validCookie(new Cookie("CUSTOM COOKIE",null,null,new ArrayList<>(),null,null,6), "Antibes");
    }

    @Then("the cookie is add to the cookie book")
    public void the_cookie_is_add_to_the_cookie_book() {
        assertEquals(4, cookieBook.getCookies().size());
    }

    @Given("a user with no fidelityAccount")
    public void aUserWithNoFidelityAccount() {
        FidelityAccount fidelityAccount = user.getFidelityAccount();
        fidelityAccount = null;
    }

    @Given("a user with a fidelityAccount")
    public void aUserWithAFidelityAccount() {
        userAction.subscribeToFidelityAccount(this.user, "name","email","pw");
        iFidelityAccountManager.addOrder(userRequest.getSubscription(this.user).get(), retrieveOrder);
    }

    @Then("he can't get an history")
    public void heCanTGetAnHistory() {
        assertTrue(historicException);
    }

    @Then("he can't receive a bill")
    public void heCanTReceiveABill() {
        assertTrue(emptyCartException);

    }

    @When("he add {int} cookie to his cart")
    public void heAddCookieToHisCart(int number) {
        userAction.addCookies(testCookie, number, cart);
    }

    @Then("there is {int} cookie in the cart")
    public void thereIsCookieInTheCart(int number) {
            Iterator iterator = user.getCart().getItemSet().iterator();
            Item item = (Item) iterator.next();
            assertEquals(number, item.getQuantity());
    }

    @Given("an store with an empty store")
    public void anStoreWithAnEmptyStore() {
        this.store.getStock().getIngredients().clear();
    }

    @Then("he can choose the cookie")
    public void heCanChooseTheCookie() {
        assertFalse(cookieList.isEmpty());
    }

    @Then("he can't choose the cookie")
    public void heCanTChooseTheCookie() {
        assertTrue(cookieList.isEmpty());
    }

    @Then("he don't have a past order")
    public void heDonTHaveAPastOrder() {
        assertTrue(historic.isEmpty());
    }

    @When("a client got a fidelity account")
    public void aClientGotAFidelityAccount() throws Exception {
        userAction.subscribeToFidelityAccount(this.user, "name","email","pw");
        historic = userRequest.getHistory(user.getFidelityAccount());
    }

    @When("when a brandManager add a cookie that is already in the cookie book")
    public void whenABrandManagerAddACookieThatIsAlreadyInTheCookieBook() {
        try {
            brandManagerActions.validCookie(new Cookie("Cookie au chocolat",null,null,new ArrayList<>(),null,null,6), "Antibes");
        } catch (Exception e) {
            this.exception=e;
        }
    }

    @Then("the cookie book didn't changed")
    public void theCookieBookDidnTChanged() {
        assertNotNull(exception);
    }
}
