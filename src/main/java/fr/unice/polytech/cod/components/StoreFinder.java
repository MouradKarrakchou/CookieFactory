package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.interfaces.IStoreFinder;
import fr.unice.polytech.cod.store.AllStores;
import fr.unice.polytech.cod.store.Store;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Component that simulates a link with the company to retrieve the stores list from the company
 */
@Component
public class StoreFinder implements IStoreFinder {

    private StoreFinder() {
    }

    @Override
    public List<Store> getStores() {
        return(AllStores.getInstance().getStoreList());
    }

    @Override
    public Store findStore(String name) throws InvalidStoreException {
        for (Store store : AllStores.getInstance().getStoreList()) {
            if (store.getName().equals(name)) return store;
        }
        throw new InvalidStoreException();
    }
}
