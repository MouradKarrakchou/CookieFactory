package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.pojo.StoreLocation;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.interfaces.StoreFinder;
import fr.unice.polytech.cod.store.Store;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StoreFinderComponent implements StoreFinder {
    @Override
    public List<Store> getStores(StoreLocation storeLocation) {
        return null; //TODO
    }

    @Override
    public Store findStore(StoreLocation storeLocation, String name) throws InvalidStoreException {
        return null; //TODO
    }
}
