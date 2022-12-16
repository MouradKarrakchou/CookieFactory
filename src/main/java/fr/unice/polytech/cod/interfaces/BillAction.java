package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;

public interface BillAction {

    double calculateTotalPrice(Bill bill);

    String returnBill(Bill bill);
}
