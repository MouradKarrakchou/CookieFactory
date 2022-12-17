package fr.unice.polytech.cod.components;

import fr.unice.polytech.cod.exceptions.FidelityAccountAlreadyExistException;
import fr.unice.polytech.cod.exceptions.InvalidStoreException;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.interfaces.*;
import fr.unice.polytech.cod.pojo.order.Bill;
import fr.unice.polytech.cod.pojo.order.Order;
import fr.unice.polytech.cod.pojo.order.OrderState;
import fr.unice.polytech.cod.pojo.Item;
import fr.unice.polytech.cod.pojo.schedule.Interval;
import fr.unice.polytech.cod.pojo.store.AllStores;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.Cart;
import fr.unice.polytech.cod.pojo.user.User;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.FidelityAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.List;

@Component
public class UserHandler implements UserAction {
    @Autowired
    IFidelityAccountManager IFidelityAccountManager;
    @Autowired
    CartActions cartActions;
    @Autowired
    CartPenalty cartPenalty;
    @Autowired
    IStoreFinder IStoreFinder;
    @Autowired
    IIntervalManager intervalManager;

    AllStores allStores=AllStores.getInstance();

    /**
     * Add cookies to cart
     *
     * @param cookie The cookie to add to the cart
     * @param quantity The quantity of the selected cookie
     */
    @Override
    public boolean addCookies(Cookie cookie, int quantity, Cart cart) {
        return cartActions.addToCart(cart, new Item(cookie, quantity));
    }

    @Override
    public boolean removeCookies(Cookie cookie, int quantity, Cart cart) {
        return cartActions.removeFromCart(cart, new Item(cookie, quantity));
    }

    /**
     * choose the store for his current order
     *
     * @param name name
     * @return store
     * @throws InvalidStoreException exception
     */
    @Override
    public Store selectStore(String name, Cart cart) throws InvalidStoreException {
        Store store = IStoreFinder.findStore(name);
        cart.setStore(store);
        return (store);
    }

    @Override
    public void chooseInterval(Interval interval, Cart cart) {
        intervalManager.reserve(interval);
        cart.setInterval(interval);
    }

    @Override
        public Bill validateCart(User user) throws Exception {
        Cart cart=user.getCart();
        Instant time = Instant.now();
        if (!cartActions.isEmpty(cart) && !cartPenalty.isTherePenalty(cart, time))
            return cartActions.validate(cart, user);
        else
            throw new Exception("Panier vide impossible de le valider");    }


    @Override
    public void subscribeToFidelityAccount(User user, String name, String email, String password) throws FidelityAccountAlreadyExistException {
        if(user.getFidelityAccount() != null) throw new FidelityAccountAlreadyExistException();
        user.setFidelityAccount(new FidelityAccount(name, email, password));
    }


    @Override
    public boolean cancelOrder(Cart cart, List<Order> userOrders, Order order) {

        if (userOrders.contains(order) && order.getOrderState().equals(OrderState.PENDING)) {
            cartActions.cancelOrder(cart, order);
            return true; //Your order has been canceled
        } else
            return false; //Your order is already in progress. You cannot canceled it

    }

}