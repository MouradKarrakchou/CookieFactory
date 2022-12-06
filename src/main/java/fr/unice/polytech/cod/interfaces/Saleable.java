package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.store.Store;

public interface Saleable {
    String getName();

    double getPriceByStore(Store store);

    double getPriceHT();
}