package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.Item;
import fr.unice.polytech.cod.Order;
import fr.unice.polytech.cod.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Store {
    List<Order> orderList;
    private final Stock stock;

    public Store() {
        this.orderList = new ArrayList<>();
        this.stock = new Stock();
    }

    public void retrieveOrder(Order order) {
        this.orderList.remove(order);
    }

    /**
     * For a given set of ingredients check if there is enough of these ingredients in the stock.
     *
     * @param ingredients - The given set of ingredients to check.
     * @return boolean - If there is enough of these ingredients in the stock.
     */
    public boolean hasEnough(Set<Ingredient> ingredients) {
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
}


