import fr.unice.polytech.cod.Cart;
import fr.unice.polytech.cod.CookieBook;
import fr.unice.polytech.cod.FidelityAccount;

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
    public void seeCatalogue(){
    }

    /**
     * Add cookies to the Cart
     */
    public void chooseCookies(){
        //use Cart
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
