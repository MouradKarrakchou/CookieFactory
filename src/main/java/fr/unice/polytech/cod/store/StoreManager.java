package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.ingredient.Cookie;

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

    public void addCookieStore(Cookie cookie, String store) throws Exception{
        Store store1 = this.selectStore(store);
        store1.getCookieBook().addCookieRecipe(cookie);
    }
}
