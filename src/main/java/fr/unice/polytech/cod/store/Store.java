package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.*;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;

import java.util.*;

public class Store {
    String name;
    List<Order> orderList;
    List<Chef> listChef;
    private final Stock stock;
    public static int orderNumber = 0;
    Map<Ingredient, Double> taxes;
    CookieBook cookieBook;
    public TimeClock openHour=new TimeClock(8,0);
    public TimeClock closeHour=new TimeClock(18,0);

    public Store(String name) {
        this.name=name;
        listChef=new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.stock = new Stock();
        listChef.add(new Chef(this));
        this.cookieBook = new CookieBook();

        for(Ingredient ingredient : stock.getIngredients()) {
            taxes.put(ingredient, 0.0);
        }
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
    public List<Interval> timeSlotAvailables(int minutes){
        List<Interval> intervals = new ArrayList<>();
        for (Chef chef:listChef){
            for (Interval interval: chef.getIntervalsAvailable(minutes))
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
}


