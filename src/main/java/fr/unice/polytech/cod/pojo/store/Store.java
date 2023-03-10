package fr.unice.polytech.cod.pojo.store;

import fr.unice.polytech.cod.pojo.CookieBook;
import fr.unice.polytech.cod.pojo.Stock;
import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.helper.tools.TimeClock;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.FidelityAccount;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private String name;
    private List<Order> orderList;
    private List<FidelityAccount> fidelityAccountList;
    private List<Chef> listChef;
    private final Stock stock;
    public static int orderNumber = 0;
    private CookieBook cookieBook;
    private TimeClock openHour = new TimeClock(8,0);
    private TimeClock closeHour = new TimeClock(18,0);
    private final List<SurpriseBasket> surpriseBaskets;

    private double tax = 0;

    public Store(String name) {
        this.name=name;
        this.listChef=new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.fidelityAccountList = new ArrayList<>();
        this.stock = new Stock();
        listChef.add(new Chef(this));
        this.cookieBook = new CookieBook();
        this.surpriseBaskets = new ArrayList<>();
    }

    public List<SurpriseBasket> getSurpriseBaskets() {
        return surpriseBaskets;
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


    public List<Chef> getListChef() {
        return listChef;
    }

    public CookieBook getCookieBook() {
        return cookieBook;
    }

    public List<FidelityAccount> getFidelityAccountList() {
        return fidelityAccountList;
    }


    public void setName(String name) {
        this.name = name;
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

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}


