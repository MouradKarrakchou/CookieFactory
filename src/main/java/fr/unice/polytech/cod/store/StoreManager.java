package fr.unice.polytech.cod.store;

import java.util.ArrayList;
import java.util.List;

public class StoreManager {
    List<Store> storeList;
    public StoreManager(){
        storeList= new ArrayList<>();
        storeList.add(new Store("Antibes"));
        storeList.add(new Store("Nice"));
        storeList.add(new Store("Sophia"));
    }

    public List<Store> getStoreList() {
        return storeList;
    }
    public Store selectStore(String name) throws InvalidStoreException {
        for (Store store:storeList){
            if (store.getName().equals(name)) return store;
        }
        throw new InvalidStoreException();
    }
}
