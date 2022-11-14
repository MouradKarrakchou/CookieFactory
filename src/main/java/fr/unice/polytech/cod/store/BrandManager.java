package fr.unice.polytech.cod.store;

import fr.unice.polytech.cod.CookieBook;

public class BrandManager {
    CookieBook cookieBook;

    public BrandManager(CookieBook cookieBook) {
        this.cookieBook = cookieBook;
    }

    public CookieBook getCookieBook() {
        return cookieBook;
    }
}
