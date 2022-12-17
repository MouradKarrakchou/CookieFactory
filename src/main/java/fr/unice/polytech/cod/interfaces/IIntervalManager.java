package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.schedule.Interval;

public interface IIntervalManager {

    void reserve(Interval interval);

    void validate(Interval interval, Order order);

    void freedInterval(Interval interval);
}