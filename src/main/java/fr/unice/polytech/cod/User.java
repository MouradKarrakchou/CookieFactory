package fr.unice.polytech.cod;
import fr.unice.polytech.cod.store.Store;

import java.util.ArrayList;
import java.util.List;

public class User {
    private CookieBook cookieBook;
    private Cart cart;
    private FidelityAccount fidelityAccount;
    private List<Order> userOrders;
    // TODO : private List<Order> orders;

    public User(CookieBook cookieBook, Cart cart, FidelityAccount fidelityAccount){
        this.cookieBook = cookieBook;
        this.cart = cart;
        this.fidelityAccount = fidelityAccount;
        this.userOrders = new ArrayList<>();
    }

    public User() {
        //for cumcumber test
        this.cookieBook = new CookieBook();
        this.cart = new Cart(new Store());
    }

    /**
     * Show the catalogue for fr.unice.polytech.cod.User
     */
    public List<Cookie> viewCatalog(){
        Display.displayCookies(cookieBook.getAvailableCookie());
        return cookieBook.getAvailableCookie();
    }


    /**
     * Add cookies to cart
     * @param cookie the cookie to add to the cart
     * @param quantity of the selected cookie
     */
    public void chooseCookies(Cookie cookie, int quantity){
        Item item = new Item(cookie, quantity);
        cart.addToCart(item);
    }

    /**
     * Show all the cookies in our order and give the choice to validate or add/delete more cookies
     */
    public void recapCart(){
        Display.title("Your cart:");
        cart.showCart();
    }

    /**
     * Add the user order to his orders list
     */
    public void validateCart(){
        userOrders.add(this.cart.createOrder());
    }
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
