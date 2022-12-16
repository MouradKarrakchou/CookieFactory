package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.pojo.CookieBook;
import fr.unice.polytech.cod.pojo.Stock;
import fr.unice.polytech.cod.decorator_pattern.PartyCookieComponent;
import fr.unice.polytech.cod.food.ingredient.Ingredient;
import fr.unice.polytech.cod.helper.MailNotifier;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.TimeClock;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    String name;
    List<Order> orderList;
    List<SurpriseBasket> surpriseBaskets;
    List<FidelityAccount> fidelityAccountList;
    List<Chef> listChef;
    public final Stock stock;
    public static int orderNumber = 0;
    Map<Ingredient, Double> taxes;
    CookieBook cookieBook;
    public TimeClock openHour = new TimeClock(8,0);
    public TimeClock closeHour = new TimeClock(18,0);
    PartyCookieStoreManager partyCookieStoreManager;

    public Store(String name) {
        this.name=name;
        this.listChef=new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.surpriseBaskets = new ArrayList<>();
        this.fidelityAccountList = new ArrayList<>();
        this.stock = new Stock();
        listChef.add(new Chef(this));
        this.cookieBook = new CookieBook();
        this.partyCookieStoreManager = new PartyCookieStoreManager();

        taxes = new HashMap<>();
    }


    public void setOrderList(List<Order> orderList) {
        this.orderList = new ArrayList<>(orderList);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public TimeClock getOpenHour() {
        return openHour;
    }

    public TimeClock getCloseHour() {
        return closeHour;
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

    public List<SurpriseBasket> getSurpriseBaskets() {
        return surpriseBaskets;
    }


    public void addFidelityAccount(FidelityAccount fidelityAccount, int todayDay, int day, int hour, int minute) {
        int waitingDay = Math.abs(day - todayDay);
        int waitingTime = waitingDay*24*60*60*1000; //days in milliseconds
        waitingTime += hour*60*60*1000; //hours in milliseconds
        waitingTime += minute*60*1000; //minute in milliseconds
        MailNotifier mailNotifier = new MailNotifier(waitingTime, this, fidelityAccount);
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


    public boolean hasPartyChef() {
        return this.partyCookieStoreManager.hasPartyChef();
    }

    public ArrayList<PartyCookieComponent> getPartyCookies() {
        return this.partyCookieStoreManager.getPartyCookies();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurpriseBaskets(List<SurpriseBasket> surpriseBaskets) {
        this.surpriseBaskets = surpriseBaskets;
    }

    public void setFidelityAccountList(List<FidelityAccount> fidelityAccountList) {
        this.fidelityAccountList = fidelityAccountList;
    }

    public void setListChef(List<Chef> listChef) {
        this.listChef = listChef;
    }

    public static void setOrderNumber(int orderNumber) {
        Store.orderNumber = orderNumber;
    }

    public void setCookieBook(CookieBook cookieBook) {
        this.cookieBook = cookieBook;
    }

    public void setOpenHour(TimeClock openHour) {
        this.openHour = openHour;
    }

    public void setCloseHour(TimeClock closeHour) {
        this.closeHour = closeHour;
    }

    public void setPartyCookieStoreManager(PartyCookieStoreManager partyCookieStoreManager) {
        this.partyCookieStoreManager = partyCookieStoreManager;
    }
}


