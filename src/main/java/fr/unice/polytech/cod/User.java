package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.InvalidStoreExepection;
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
    private List<Order> userOrdersHistory;

    private Optional<FidelityAccount> subscription;

    public User(CookieBook cookieBook, Cart cart) {
        this.cookieBook = cookieBook;
        this.cart = cart;
        this.userOrders = new ArrayList<>();
        this.userOrdersHistory = new ArrayList<>();
        this.storeManager=new StoreManager();
        this.subscription = Optional.empty();
    }

    public User() {
        //for cumcumber test
        this.cookieBook = new CookieBook();
        this.cart = new Cart();
        this.userOrders = new ArrayList<>();
        this.userOrdersHistory = new ArrayList<>();
        this.storeManager=new StoreManager();
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

    public List<Store> viewStoreAvailable(){
        return(storeManager.getStoreList());
    }

    /**
     * choose the store for his current order
     * @param name
     * @return
     * @throws InvalidStoreExepection
     */
    public Store selectStore(String name) throws InvalidStoreExepection {
        Store store=storeManager.selectStore(name);
        this.cart.setStore(store);
        return(store);
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
     * @return
     */
    public List<TimeSlot> getAvailableTimeSlot(){
        return(this.cart.getStore().timeSlotAvailables());
    }

    public void chooseTimeSlot(TimeSlot timeSlot){
        timeSlot.setReserved(true);
        this.cart.setTimeSlot(timeSlot);
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

    public void retrieveOrder(Order order) {
        if (userOrders.contains(order)) {
            userOrdersHistory.add(order);
            userOrders.remove(order);
            order.setState(OrderState.RETRIEVE);
        }
    }

    public void addOrder(Order order) {
        this.userOrders.add(order);
        if(this.subscription.isPresent())
            this.subscription.get().addOrder(order);
    }

    public List<Order> getOrders() {
        return userOrders;
    }

    public List<Order> getUserOrdersHistory() {
        return userOrdersHistory;
    }

    public List<Item> getAllItemsFromCart() {
        return cart.getItemList();
    }

    public Item getItemFromCart(String itemName) throws Exception {
        return cart.getItem(itemName);
    }

    public void removeOneItemFromCart(Item item) {
        cart.removeOneFromCart(item);
    }

    public void subscribeToFidelityAccount(String name, String email, String password){
        this.subscription = Optional.of(new FidelityAccount(name, email, password));
    }

    public Optional<FidelityAccount> getSubscription() {
        return subscription;
    }
}
