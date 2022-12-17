package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.Stock;
import fr.unice.polytech.cod.store.Store;

public interface Saleable {
    String getName();

    double getPrice(Store store);
}
