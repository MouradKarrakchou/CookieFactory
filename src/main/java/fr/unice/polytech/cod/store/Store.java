package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.data.CookieBook;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.helper.UpdatableObject;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;

import java.util.*;

public class Store extends UpdatableObject {
    String name;
    List<Order> orderList;
    List<Order> obsoleteOrders;
    List<SurpriseBasket> surpriseBaskets;
    List<FidelityAccount> fidelityAccountList;
    List<Chef> listChef;
    private final Stock stock;
    public static int orderNumber = 0;
    Map<Ingredient, Double> taxes;
    CookieBook cookieBook;
    public TimeClock openHour=new TimeClock(8,0);
    public TimeClock closeHour=new TimeClock(18,0);
    StoreManager storeManager;

    public Store(String name) {
        super(3*60*60*1000); //3 hours
        this.name=name;
        listChef=new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.obsoleteOrders = new ArrayList<>();
        this.surpriseBaskets = new ArrayList<>();
        this.fidelityAccountList = new ArrayList<>();
        this.stock = new Stock();
        listChef.add(new Chef(this));
        this.cookieBook = new CookieBook();

        taxes = new HashMap<>();
        startTimer();
    }

    public void retrieveOrder(Bill bill) throws Exception{
        Order order = bill.getOrder();
        if (this.orderList.contains(order)){
            order.updateState(OrderState.RETRIEVE);
            this.orderList.remove(order);
        }else
            throw new Exception("Order doesn't exist");
    }

    /**
     * Gets a list of available TimeSlots from all the employees of the store by Date;
     * @return
     */
    public List<Interval> timeSlotAvailables(int minutes,int numberOfDaysBeforeTheOrder){
        List<Interval> intervals = new ArrayList<>();
        for (Chef chef:listChef){
            for (Interval interval: chef.getIntervalsAvailable(minutes,numberOfDaysBeforeTheOrder))
                if (!intervals.contains(interval)) intervals.add(interval);
        }
        Collections.sort(intervals);
        return(intervals);
    }

    /**
     * For a given set of ingredients check if there is enough of these ingredients in the stock.
     *
     * @param ingredients - The given set of ingredients to check.
     * @return boolean - If there is enough of these ingredients in the stock.
     */
    public boolean hasEnoughIngredients(Set<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients)
            if (!stock.hasEnough(ingredient)) return false;
        return true;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = new ArrayList<>(orderList);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order, Set<Ingredient> ingredientsNeeded) {
        for(Ingredient ingredient : ingredientsNeeded)
            stock.lock(ingredient);
        this.orderList.add(order);
    }

    public void removeOrder(Order order) {
        this.orderList.remove(order);
    }

    public void associateOrder(Chef chef, Order orderToPrepare) {
        if(chef.isAvailable()) {
            chef.associateOrder(orderToPrepare);
            orderToPrepare.updateState(OrderState.IN_PROGRESS);
        }
    }

    /**
     * change the openig hour of the restaurant(to do during the night because it reload all the schedule)
     * @param open
     * @param close
     */
    public void changeOpeningHour(TimeClock open,TimeClock close){
        this.openHour=open;
        this.closeHour=close;
        for (Chef chef: listChef){
            chef.updateSchedule(this);
        }
    }


    public void setStoreManager(StoreManager storeManager) {
        this.storeManager = storeManager;
    }

    public StoreManager getStoreManager() {
        return storeManager;
    }

    public TimeClock getOpenHour() {
        return openHour;
    }

    public TimeClock getCloseHour() {
        return closeHour;
    }

    public void addChef(Chef chef){
        this.listChef.add(chef);
    }

    public Stock getStock() {
        return this.stock;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, Double> getTaxes() {
        return taxes;
    }

    public void setTaxes(Map<Ingredient, Double> taxes) {
        this.taxes = taxes;
    }

    public List<Chef> getListChef() {
        return listChef;
    }

    public CookieBook getCookieBook() {
        return cookieBook;
    }

    /**
     * Check if there are new obsoletes orders for Too Good to Go
     */
    private void checkObsoleteOrders() {
        if(!obsoleteOrders.isEmpty())
            for(Order order : obsoleteOrders)
                surpriseBaskets.add(new SurpriseBasket(order));
    }

    /**
     * Add the order to the obsolete orders list
     * @param order to add
     */
    public void addToObsoleteOrders(Order order) {
        obsoleteOrders.add(order);
    }

    public List<SurpriseBasket> getSurpriseBaskets() {
        return surpriseBaskets;
    }

    //TODO start timer at the open hour + 3h and stop it at the close hour
    @Override
    protected void OnTimeReached() {
        checkObsoleteOrders();
        startTimer();
    }

    public void addFidelityAccount(FidelityAccount fidelityAccount, int todayDay, int day, int hour, int minute) {
        int waitingDay = Math.abs(day - todayDay);
        int waitingTime = waitingDay*24*60*60*1000; //days in milliseconds
        waitingTime += hour*60*60*1000; //hours in milliseconds
        waitingTime += minute*60*1000; //minute in milliseconds
        MailNotifier mailNotifier = new MailNotifier(waitingTime, this, fidelityAccount);
        startTimer();
        mailNotifier.OnTimeReached();
        fidelityAccountList.add(fidelityAccount);
    }

    public void fillStock(List<Ingredient> ingredientList, Map<Ingredient, Double> taxesValues) {
        stock.addStockList(ingredientList);
        for(Ingredient ingredient : ingredientList) {
            if(!taxes.containsKey(ingredient))
                this.updateTaxes(ingredient, taxesValues.get(ingredient));
        }
    }

    private void updateTaxes(Ingredient ingredient, double tax) {
        taxes.put(ingredient, tax);
    }

    /**
     * Return the available cookies based on the stock of the store
     * @return The list of available cookies
     */
    public List<Cookie> getAvailableCookie() {
        List<Cookie> cookieAvailable = new ArrayList<>();
        cookieBook.getCookies().stream()
                .filter(cookie -> hasEnoughIngredients(cookie.getIngredients()))
                .forEach(cookieAvailable::add);
        return cookieAvailable;
    }
}


