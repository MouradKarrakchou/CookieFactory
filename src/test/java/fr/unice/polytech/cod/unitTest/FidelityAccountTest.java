package fr.unice.polytech.cod.unitTest;

import fr.unice.polytech.cod.interfaces.IFidelityAccountManager;
import fr.unice.polytech.cod.pojo.store.Store;
import fr.unice.polytech.cod.pojo.user.User;
import fr.unice.polytech.cod.pojo.user.fidelityAccount.FidelityAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FidelityAccountTest {
    User user;
    Store store;
    FidelityAccount fidelityAccount;
    int todayDay;
    int day;
    int hour;
    int minute;

    @Autowired
    IFidelityAccountManager iFidelityAccountManager;

    @Test
    void subscribeToSurpriseBasketTest() {
        store = new Store("Antibes");
        user = new User();
        user.getCart().setStore(store);
        fidelityAccount = new FidelityAccount();
        user.setFidelityAccount(fidelityAccount);

        day = 1;
        hour = 10;
        minute = 30;

        iFidelityAccountManager.subscribeToSurpriseBasket(fidelityAccount, store, todayDay, day, hour, minute);

        assertTrue(store.getFidelityAccountList().contains(fidelityAccount));
    }
}
