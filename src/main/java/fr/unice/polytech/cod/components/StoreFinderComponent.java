package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.pojo.StoreLocation;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.interfaces.StoreFinder;
import fr.unice.polytech.cod.store.Store;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StoreFinderComponent implements StoreFinder {
    private final List<Store> storeList;

    private StoreFinderComponent() {
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
    public static StoreFinderComponent getInstance(){
        return new StoreFinderComponent();
    }
}
