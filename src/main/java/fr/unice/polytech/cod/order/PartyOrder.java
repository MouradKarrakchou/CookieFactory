package fr.unice.polytech.cod.order;

import fr.unice.polytech.cod.user.Cart;
import fr.unice.polytech.cod.user.User;

public class PartyOrder extends Order{
    public PartyOrder(Cart cart, User user) {
        super(cart, user);
        super.totalPrice += super.totalPrice*25/100; //marge
    }
}
