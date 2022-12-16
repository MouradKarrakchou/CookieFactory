package fr.unice.polytech.cod.interfaces;

import fr.unice.polytech.cod.user.Cart;

import java.time.Instant;

public interface CartPenalty {

    /**
     * Manage the necessity of the penalty.
     *
     * @param cart - Cart.
     * @param time - At which the client has canceled his order.
     */
    void cancelPenalty(Cart cart, Instant time);

    /**
     * Check if the penalty is over and if so, update the attribute.
     *
     * @param cart - The cart.
     * @param time - At which we check if the there is the penalty.
     * @return If there still is the penalty.
     */
    boolean isTherePenalty(Cart cart, Instant time);

    /**
     * Check if another order has been canceled less than 8 minutes ago.
     *
     * @param cart - The cart.
     * @param time - At which the order has been canceled.
     * @return If 2 orders has been canceled in 8 minutes or less.
     */
    boolean isCanceledTwiceInARow(Cart cart,Instant time);


    /**
     * Apply the 10 minutes penalty
     *
     * @param cart - The cart.
     * @param time - At which the order has been canceled and at which the penalty starts.
     */
    void penalty(Cart cart,Instant time);

}