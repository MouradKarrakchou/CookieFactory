package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Chef;

import java.util.ArrayList;
import java.util.List;

public class Schedule {

    List<DaySlot> daySlotList;

    public Schedule() {
        //a finir
        daySlotList=new ArrayList<>();
        daySlotList.add(new DaySlot(null));
    }

    public void associateOrder(Chef chef, Order orderToPrepare) {
        if(chef.isAvailable()) {
            chef.associateOrder(orderToPrepare);
            orderToPrepare.setState(OrderState.IN_PROGRESS);
        }
    }

    public void startTimer() {
        //TODO lance le compteur de surement dans un thread à part
    }
}
