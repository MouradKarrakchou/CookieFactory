package fr.unice.polytech.cod.components;


import fr.unice.polytech.cod.pojo.CookieBook;
import fr.unice.polytech.cod.food.Item;
import fr.unice.polytech.cod.interfaces.ItemActions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ItemComponentTest {

    @Autowired
    private ItemActions itemActions;

    @Test
    public void testDeTest() {
        CookieBook cookieBook = new CookieBook();
        assertEquals(0, itemActions.getQuantity(new Item(cookieBook.getCookies().get(0), 6)));
    }
}
