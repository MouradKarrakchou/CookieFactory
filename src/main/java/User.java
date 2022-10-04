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
}
