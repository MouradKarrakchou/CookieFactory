package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.interfaces.StoreFinder;
import fr.unice.polytech.cod.store.Store;

import java.util.List;

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
