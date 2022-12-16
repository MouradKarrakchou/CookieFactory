package fr.unice.polytech.cod.pojo;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreLocation {
    private final List<Store> storeList;
    public static StoreLocation instance = new StoreLocation();

    private StoreLocation(){
        storeList = new ArrayList<>();
        storeList.add(new Store("Antibes"));
        storeList.add(new Store("Nice"));
        storeList.add(new Store("Sophia"));
    }

    public List<Store> getStoreList() {
        return storeList;
    }
    public static StoreLocation getInstance(){
        return new StoreLocation();
    }
}
