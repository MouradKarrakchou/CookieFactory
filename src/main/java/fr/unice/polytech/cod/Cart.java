package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Store store;
    private List<Item> itemList;

    public Cart(Store store) {
        this.store = store;
        itemList = new ArrayList<>();
    }
    public void showCart() {
        for(Item item: itemList){
            Display.itemInfo(item);
        }
    }

    public void addToCart(Item item) {
        itemList.add(item);
    }

    public List<Item> getItemList() {
        return itemList;
    }
}
