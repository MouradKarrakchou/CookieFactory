package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.store.Store;

import java.util.List;

public interface StoreFinder {
    List<Store> getStores(StoreLocation storeLocation);
    Store findStore(StoreLocation storeLocation, String name) throws InvalidStoreException;
}
