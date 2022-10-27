package fr.unice.polytech.cod;

import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class User {
    private CookieBook cookieBook;
    private Cart cart;
    private FidelityAccount fidelityAccount;
    private List<Order> userOrders;

    private List<Order> userOrdersHistory;

    public User(CookieBook cookieBook, Cart cart, FidelityAccount fidelityAccount) {
        this.cookieBook = cookieBook;
        this.cart = cart;
        this.fidelityAccount = fidelityAccount;
        this.userOrders = new ArrayList<>();
        this.userOrdersHistory = new ArrayList<>();
    }

    public User() {
        //for cumcumber test
        this.cookieBook = new CookieBook();
        this.cart = new Cart(new Store());
        this.userOrders = new ArrayList<>();
        this.userOrdersHistory = new ArrayList<>();
    }

    /**
     * Show the catalogue
     */
    public List<Cookie> viewCatalog() {
        Display.displayCookies(cookieBook.getAvailableCookie(this.cart.getStore()));
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

    /**
     * Show all the cookies in our order and give the choice to validate or add/delete more cookies
     */
    public void recapCart() {
        Display.title("Your cart:");
        cart.showCart();
    }

    /**
     * If the cart is not empty, validate the cart to create an order
     */
    public void validateCart() throws Exception {
        //userOrders.add(this.cart.createOrder());
        if (!cart.isEmpty())
            cart.validateCart(this);
        else
            throw new Exception("Panier vide impossible de le valider");
    }

    public Cart getCart() {
        return cart;
    }

    /**
     * If the cart is validated, the client can finalise the order to get his bill
     * @return the bill of the order
     * @throws Exception
     */
    public Bill finaliseOrder() throws Exception {
        if (cart.isValidated())
            return new Bill();
        else
            throw new Exception("Panier non validé");
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
        cart.removeFromCart(item);
    }
}
