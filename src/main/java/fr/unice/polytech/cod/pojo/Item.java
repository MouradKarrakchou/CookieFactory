package fr.unice.polytech.cod.pojo;

import fr.unice.polytech.cod.food.Cookie;
import java.io.Serializable;
import java.util.Objects;

/**
 * Pojo class : Item
 */
public class Item implements Serializable {
    private final Cookie cookie;
    private int quantity;

    // Constructor //
    public Item(Cookie cookie, int quantity) {
        this.quantity = quantity;
        this.cookie = cookie;
    }

    // Getters //
    public int getQuantity() {
        return quantity;
    }

    public Cookie getCookie() {
        return cookie;
    }

    // Setters //
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Overrides //
    @Override
    public int hashCode() {
        return Objects.hash(cookie.getName());
    }

    @Override
    public boolean equals(Object object){
        // Check for the address
        if (object == this)
            return true;

        // Check for the instance
        if (!(object instanceof Item item))
            return false;

        // Check for the name equality
        return item.cookie.getName().equals(cookie.getName());
    }

}