package fr.unice.polytech.cod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.*;

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

    /**
     * Show the catalogue for fr.unice.polytech.cod.User
     */
    public List<Cookie> seeCatalogue(){
        System.out.println(Arrays.toString(cookieBook.seeCatalogue().toArray()));

        return(cookieBook.seeCatalogue());
    }


    /**
     * Add cookies to cart
     * @param cookie the cookie to add to the cart
     * @param quantity of the selected cookie
     */
    public void chooseCookies(Cookie cookie, Integer quantity){
        Item item = new Item(cookie, quantity);
        cart.addToCart(item);
    }

    /**
     * Show all the cookies in our order and give the choice to validate or add/delete more cookies
     */
    public void recapCart(){
        this.cart.showCart();
    }

    /**
     * Add the user order to his orders list
     * @return the user order
     */
    public Order validateCart(){
        Order order = new Order(cart, OrderState.PENDING);
        userOrders.add(order);
        return order;
    }


}
