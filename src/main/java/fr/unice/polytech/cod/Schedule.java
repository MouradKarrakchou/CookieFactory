package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Chef;

public class Schedule {



    Schedule() {

    }

    public void associateOrder(Chef chef, Order orderToPrepare) {
        if(chef.isAvailable()) {
            chef.setOrder(orderToPrepare);
            chef.setAvailability(false);
            orderToPrepare.setState(OrderState.IN_PROGRESS);
        }
    }

    public void startTimer() {
        //TODO lance le compteur de surement dans un thread à part
    }
}
