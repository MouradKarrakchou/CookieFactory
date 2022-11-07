package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.*;
import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.*;

public class Store {
    String name;
    List<Order> orderList;
    List<Chef> listChef;
    private final Stock stock;

    public Store(String name) {
        this.name=name;
        listChef=new ArrayList<>();
        this.orderList = new ArrayList<>();
        this.stock = new Stock();
        listChef.add(new Chef(new Schedule()));
    }

    public void retrieveOrder(Order order) {
        this.orderList.remove(order);
        order.getUser().retrieveOrder(order);
    }

    /**
     * Gets a list of available TimeSlots from all the employees of the store by Date;
     * @return
     */
    public List<TimeSlot> timeSlotAvailables(){
        List<TimeSlot> timeSlots = new ArrayList<>();
        for (Chef chef:listChef){
            timeSlots.addAll(chef.getTimeSlotsAvailables());
        }
        return(timeSlots);
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

    public void associateOrder(Chef chef, Order orderToPrepare) {
        if(chef.isAvailable()) {
            chef.associateOrder(orderToPrepare);
            orderToPrepare.setState(OrderState.IN_PROGRESS);
        }
    }



    public Stock getStock() {
        return this.stock;
    }


    public String getName() {
        return name;
    }
}


