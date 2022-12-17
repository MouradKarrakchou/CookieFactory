package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.schedule.Interval;

public interface IIntervalManager {
    void reserve(Interval interval);
    void validate(Interval interval, Order order);
    void freedInterval(Interval interval);

}