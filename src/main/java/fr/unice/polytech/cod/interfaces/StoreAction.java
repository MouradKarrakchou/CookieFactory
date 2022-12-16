package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.store.Store;

public interface StoreAction {
    void checkObsoleteOrders(Store store);
}
