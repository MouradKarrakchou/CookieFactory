import fr.unice.polytech.cod.*;

import java.util.List;

public class User {
    private CookieBook cookieBook;
    private Cart cart;
    private FidelityAccount fidelityAccount;
    // TODO : private List<Order> orders;

    public User(CookieBook cookieBook, Cart cart, FidelityAccount fidelityAccount){
        this.cookieBook = cookieBook;
        this.cart = cart;
        this.fidelityAccount = fidelityAccount;
    }

    /**
     * Show the catalogue for User
     */
    public List<Cookie> seeCatalogue(){
        System.out.println(cookieBook.seeCatalogue());
        return(cookieBook.seeCatalogue());
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

    }


}
