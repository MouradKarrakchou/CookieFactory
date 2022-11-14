package fr.unice.polytech.cod;

import fr.unice.polytech.cod.ingredient.Cookie;
import fr.unice.polytech.cod.order.Order;
import fr.unice.polytech.cod.order.OrderState;
import fr.unice.polytech.cod.store.InvalidStoreException;
import fr.unice.polytech.cod.store.Store;
import fr.unice.polytech.cod.store.StoreManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User {
    private CookieBook cookieBook;
    private Cart cart;
    private List<Order> userOrders;
    private StoreManager storeManager;

    private Optional<FidelityAccount> subscription;

    public User(CookieBook cookieBook, Cart cart, StoreManager storeManager) {
        this.cookieBook = cookieBook;
        this.cart = cart;
        this.userOrders = new ArrayList<>();
        this.storeManager = storeManager;
        this.subscription = Optional.empty();
    }

    /**
     * return the list of available cookies based on the store
     */
    public List<Cookie> viewCatalog() {
        //Display.displayCookies(cookieBook.getAvailableCookie(this.cart.getStore())); //useless non ?
        return cookieBook.getAvailableCookie(this.cart.getStore());
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
        return (storeManager.getStoreList());
    }

    /**
     * choose the store for his current order
     *
     * @param name
     * @return
     * @throws InvalidStoreException
     */
    public Store selectStore(String name) throws InvalidStoreException {
        Store store = storeManager.selectStore(name);
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
        //userOrders.add(this.cart.createOrder());
        if (!cart.isEmpty())
            return cart.validate(this);
        else
            throw new Exception("Panier vide impossible de le valider");
    }

    public Cart getCart() {
        return cart;
    }

    public void addOrder(Order order) {
        this.userOrders.add(order);
        if (this.subscription.isPresent())
            this.subscription.get().addOrder(order);
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

    public StoreManager getStoreManager() {
        return storeManager;
    }

    public Store getStore() {
        return (this.cart.getStore());
    }

    public void removeOneItemFromCart(Item item) {
        cart.removeOneFromCart(item);
    }

    public void subscribeToFidelityAccount(String name, String email, String password) {
        this.subscription = Optional.of(new FidelityAccount(name, email, password));
    }

    public Optional<FidelityAccount> getSubscription() {
        return subscription;
    }

    public boolean hasDiscount() {
        if (this.getSubscription().isEmpty()) return false;
        FidelityAccount subscription = this.getSubscription().get();
        return subscription.getDiscount().isPresent();
    }

    public Optional<Discount> getDiscount() {
        return subscription.get().getDiscount();
    }

    public void useDiscount(Order order) {
        order.setDiscount(this.getDiscount());
        this.getSubscription().get().resetDiscount();
    }

    public boolean cancelOrder(Order order) {
        if(order.getOrderState().equals(OrderState.PENDING)) {
            cart.cancelOrder(order);
            return true; //Your order has been canceled
        }
        else
            return false; //Your order is already in progress. You cannot canceled it
    }
}
