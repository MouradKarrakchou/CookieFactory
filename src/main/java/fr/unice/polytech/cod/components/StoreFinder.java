package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.interfaces.IStoreFinder;
import fr.unice.polytech.cod.store.AllStores;
import fr.unice.polytech.cod.store.Store;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Component qui simule un contacte avec l'entreprise afin de récupérer la liste des magasins de la firme
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
