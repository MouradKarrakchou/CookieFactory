package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.*;
import fr.unice.polytech.cod.ingredient.Ingredient;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;

import java.util.*;

public class Store {
    String name;
    List<Order> orderList;
    List<Order> obsoleteOrders;
    List<Chef> listChef;
    private final Stock stock;
    public static int orderNumber = 0;
    Map<Ingredient, Double> taxes;

    public Store(String name) {
        this.name=name;
        listChef=new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.obsoleteOrders = new ArrayList<>();
        this.stock = new Stock();
        listChef.add(new Chef());

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

    /**
     * Check if there are new obsoletes orders for Too Good to Go
     */
    private void checkObsoleteOrders() {

    }

    /**
     * Add the order to the obsolete orders list
     * @param order to add
     */
    public void addToObsoleteOrders(Order order) {
        obsoleteOrders.add(order);
    }
}


