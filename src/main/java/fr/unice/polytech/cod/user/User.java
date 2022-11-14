package fr.unice.polytech.cod.user;

import fr.unice.polytech.cod.data.CookieBook;
import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.helper.Display;
import fr.unice.polytech.cod.food.Cookie;
import fr.unice.polytech.cod.order.Bill;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.schedule.Interval;
import fr.unice.polytech.cod.store.InvalidStoreException;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.data.StoreLocation;
import fr.unice.polytech.cod.user.fidelityAccount.Discount;
import fr.unice.polytech.cod.user.fidelityAccount.FidelityAccount;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User {
    private Cart cart;
    private List<Order> userOrders;
    private StoreLocation storeLocation;

    private Optional<FidelityAccount> _subscription;

    public User(Cart cart, StoreLocation storeLocation) {
        this.cart = cart;
        this.userOrders = new ArrayList<>();
        this.storeLocation = storeLocation;
        this._subscription = Optional.empty();
    }

    /**
     * return the list of available cookies based on the store
     */
    public List<Cookie> viewCatalog() {
        return cart.getStore().getCookieBook().getAvailableCookie(this.cart.getStore());
    }


    /**
     * Add cookies to cart
     *
     * @param cookie   the cookie to add to the cart
     * @param quantity of the selected cookie
     */
    public boolean chooseCookies(Cookie cookie, int quantity) {
        Item item = new Item(cookie, quantity);
        return cart.addToCart(item);
    }

    public List<Store> viewStoreAvailable() {
        return (storeLocation.getStoreList());
    }

    /**
     * choose the store for his current order
     *
     * @param name
     * @return
     * @throws InvalidStoreException
     */
    public Store selectStore(String name) throws InvalidStoreException {
        Store store = storeLocation.selectStore(name);
        this.cart.setStore(store);
        return (store);
    }

    /**
     * Show all the cookies in our order and give the choice to validate or add/delete more cookies
     */
    public void recapCart() {
        Display.title("Your cart:");
        cart.showCart();
    }

    /**
     * Gets a list of available TimeSlots by Date;
     *
     * @return
     */
    public List<Interval> getAvailableIntervals(int minutesNeeded) {
        return (this.cart.getStore().timeSlotAvailables(minutesNeeded));
    }


    public void chooseInterval(Interval interval) {
        interval.reserve();
        this.cart.setInterval(interval);
    }

    /**
     * If the cart is not empty, validate the cart to create an order
     */
    public Bill validateCart() throws Exception {
        Instant time = Instant.now();
        if (!cart.isEmpty() && !cart.isTherePenalty(time))
            return cart.validate(this);
        else
            throw new Exception("Panier vide impossible de le valider");
    }

    public Cart getCart() {
        return cart;
    }

    public void addOrder(Order order) {
        this.userOrders.add(order);
        if (this._subscription.isPresent())
            this._subscription.get().addOrder(order);
    }

    public List<Order> getOrders() {
        return userOrders;
    }

    public List<Item> getAllItemsFromCart() {
        return cart.getItemList();
    }

    public Item getItemFromCart(String itemName) throws Exception {
        return cart.getItem(itemName);
    }

    public StoreLocation getStoreManager() {
        return storeLocation;
    }

    public Store getStore() {
        return (this.cart.getStore());
    }

    public void removeOneItemFromCart(Item item) {
        cart.removeOneFromCart(item);
    }

    public List<Order> getOrderHistory(){
        if (this._subscription.isPresent()) return this._subscription.get().getOrderList();
        else return new ArrayList<>();
    }
    public void subscribeToFidelityAccount(String name, String email, String password) {
        this._subscription = Optional.of(new FidelityAccount(name, email, password));
    }

    public Optional<FidelityAccount> getSubscription() {
        return _subscription;
    }

    public boolean hasDiscount() {
        if (this.getSubscription().isEmpty()) return false;
        FidelityAccount subscription = this.getSubscription().get();
        return subscription.getDiscount().isPresent();
    }

    public Optional<Discount> getDiscount() {
        return _subscription.get().getDiscount();
    }

    public void useDiscount(Order order) {
        order.setDiscount(this.getDiscount());
        this.getSubscription().get().resetDiscount();
    }

    public boolean cancelOrder(Order order) {
        if(userOrders.contains(order) && order.getOrderState().equals(OrderState.PENDING)) {
            cart.cancelOrder(order);
            return true; //Your order has been canceled
        }
        else
            return false; //Your order is already in progress. You cannot canceled it
    }

    /**
     * This simulates a sms send to the user
     * @param message The message send to the user.
     */
    public void notify(String message){
        if(_subscription.isPresent()) Display.smsOk(_subscription.get().getName(), message);
        else Display.smsNok("Anonymous account.");
    }
}
