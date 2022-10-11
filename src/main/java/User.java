import fr.unice.polytech.cod.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.*;

public class User {
    private CookieBook cookieBook;
    private Cart cart;
    private FidelityAccount fidelityAccount;
    // TODO : private List<Order> orders;
    private List<Order> orders;

    public User(CookieBook cookieBook, Cart cart, FidelityAccount fidelityAccount){
        this.cookieBook = cookieBook;
        this.cart = cart;
        this.fidelityAccount = fidelityAccount;
        this.orders = new ArrayList<>();
    }

    /**
     * Show the catalogue for User
     */
    public void seeCatalogue(){x²
    }


    /**
     * Add cookies to the Cart
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
     * lock the Cart and validate order
     */
    public void validateCartAndCreateOrder(){
        orders.add(this.cart.validateCart());
    }


}
