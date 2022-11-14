package fr.unice.polytech.cod.data;

import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.store.InvalidStoreException;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreLocation {
    List<Store> storeList;
    public StoreLocation(){
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

    public void addCookieStore(Cookie cookie, String store) throws Exception{
        Store store1 = this.selectStore(store);
        store1.getCookieBook().addCookieRecipe(cookie);
    }
}
