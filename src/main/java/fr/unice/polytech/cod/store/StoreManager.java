package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.schedule.TimeClock;

public class StoreManager {
    Store store;

    public StoreManager(Store store){
        this.store=store;
    }

    public void changeOpenningHour(TimeClock open,TimeClock close ){
        store.changeOpeningHour(open,close);
    }
}
