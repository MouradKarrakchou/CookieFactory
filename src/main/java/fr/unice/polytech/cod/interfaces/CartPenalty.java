package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.user.Cart;

import java.time.Instant;

public interface CartPenalty {

    void cancelPenalty(Cart cart, Instant time);

    /**
     * Check if the penalty is over and if so, update the attribute
     *
     * @param cart - The cart.
     * @param time - At which we check if the there is the penalty.
     * @return If there still is the penalty.
     */
    boolean isTherePenalty(Cart cart, Instant time);

    boolean isCanceledTwiceInARow(Cart cart,Instant time);

    void penalty(Cart cart,Instant time);

}
