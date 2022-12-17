package fr.unice.polytech.cod.store;

import java.util.ArrayList;
import java.util.List;

public class AllStores {
    private static AllStores INSTANCE;
    private List<Store> storeList= new ArrayList<>(List.of(
            new Store("Antibes"),
            new Store("Nice"),
            new Store("Sophia")
    ));
    private AllStores(){}

    public List<Store> getStoreList() {
        return storeList;
    }

    public static AllStores getInstance(){
        if (INSTANCE==null){
            INSTANCE=new AllStores();
        }
        return INSTANCE;
    }
}
