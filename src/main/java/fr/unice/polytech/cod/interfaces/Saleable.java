package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.store.Store;

public interface Saleable {
    String getName();

    double getPrice(Store store);
}
