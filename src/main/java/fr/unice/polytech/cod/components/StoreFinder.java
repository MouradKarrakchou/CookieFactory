package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.interfaces.IStoreFinder;
import fr.unice.polytech.cod.store.Store;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StoreFinder implements IStoreFinder {
    private List<Store> storeList;

    private StoreFinder() {
        storeList = new ArrayList<>();
        storeList.add(new Store("Antibes"));
        storeList.add(new Store("Nice"));
        storeList.add(new Store("Sophia"));
    }

    @Override
    public List<Store> getStores() {
        return storeList;
    }

    @Override
    public Store findStore(String name) throws InvalidStoreException {
        for (Store store : storeList) {
            if (store.getName().equals(name)) return store;
        }
        throw new InvalidStoreException();
    }

    @PostConstruct
    public static StoreFinder getInstance(){
        return new StoreFinder();
    }
}
