package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Bill;

public interface BillAction {

    double calculateTotalPrice(Bill bill);

    String returnBill(Bill bill);
}
