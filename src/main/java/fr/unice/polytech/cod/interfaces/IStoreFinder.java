package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.pojo.store.Store;
import java.util.List;

public interface IStoreFinder {
    List<Store> getStores();

    Store findStore(String name) throws InvalidStoreException;
}
